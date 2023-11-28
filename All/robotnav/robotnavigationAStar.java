package ArtificialIntelligence.All.robotnav;


import java.util.PriorityQueue;

public class robotnavigationAStar {

    private static class Point implements Comparable<Point> {
        int x, y, f, g, h;

        public Point(int x, int y, int g, int h) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.h = h;
            this.f = g + h;
        }

        @Override
        public int compareTo(Point other) {
            return Integer.compare(this.f, other.f);
        }
    }

    public static int shortestPath(int[][] matrix, int x1, int y1, int x2, int y2) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Check if the starting and ending positions are valid
        if (x1 < 0 || x1 >= rows || y1 < 0 || y1 >= cols || matrix[x1][y1] == 1) {
            System.out.println("Invalid starting position.");
            return -1;
        }

        if (x2 < 0 || x2 >= rows || y2 < 0 || y2 >= cols || matrix[x2][y2] == 1) {
            System.out.println("Invalid ending position.");
            return -1;
        }

        // Create a visited array to keep track of visited cells
        boolean[][] visited = new boolean[rows][cols];

        // Define the directions (up, down, left, right)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Priority queue for A* algorithm
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(x1, y1, 0, heuristic(x1, y1, x2, y2)));
        visited[x1][y1] = true;

        while (!pq.isEmpty()) {
            Point current = pq.poll();

            // Check if the current point is the destination
            if (current.x == x2 && current.y == y2) {
                System.out.println("Shortest path length: " + current.g);
                return current.g;
            }

            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                // Check if the new position is valid and not visited
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && matrix[newX][newY] == 0 && !visited[newX][newY]) {
                    int newG = current.g + 1;
                    int newH = heuristic(newX, newY, x2, y2);
                    int newF = newG + newH;

                    pq.add(new Point(newX, newY, newG, newH));
                    visited[newX][newY] = true;
                }
            }
        }

        // If the destination is not reachable
        System.out.println("No valid path found.");
        return -1;
    }

    private static int heuristic(int x1, int y1, int x2, int y2) {
        // Euclidean distance heuristic
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        // Example matrix with obstacles (1's) and free paths (0's)
        int[][] matrix = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        int x1 = 0, y1 = 0; // Starting position
        int x2 = 4, y2 = 0; // Ending position

        shortestPath(matrix, x1, y1, x2, y2);
    }
}
