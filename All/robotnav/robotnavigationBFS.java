package ArtificialIntelligence.All.robotnav;


import java.util.LinkedList;
import java.util.Queue;

public class robotnavigationBFS {

    private static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void shortestPath(int[][] matrix, int x1, int y1, int x2, int y2) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Check if the starting and ending positions are valid
        if (x1 < 0 || x1 >= rows || y1 < 0 || y1 >= cols || matrix[x1][y1] == 1) {
            System.out.println("Invalid starting position.");
        }

        if (x2 < 0 || x2 >= rows || y2 < 0 || y2 >= cols || matrix[x2][y2] == 1) {
            System.out.println("Invalid ending position.");
        }

        // Create a visited array to keep track of visited cells
        boolean[][] visited = new boolean[rows][cols];

        // Define the directions (up, down, left, right)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Create a queue for BFS
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x1, y1, 0));
        visited[x1][y1] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // Check if the current point is the destination
            if (current.x == x2 && current.y == y2) {
                System.out.println("Shortest path length: " + current.dist);
            }

            // Explore neighbors
            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                // Check if the new position is valid and not visited
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && matrix[newX][newY] == 0 && !visited[newX][newY]) {
                    queue.add(new Point(newX, newY, current.dist + 1));
                    visited[newX][newY] = true;
                }
            }
        }

        // If the destination is not reachable
        System.out.println("No valid path found.");
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
        int x2 = 4, y2 = 2; // Ending position

        shortestPath(matrix, x1, y1, x2, y2);
    }
}

