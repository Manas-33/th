package ArtificialIntelligence.All.robotnav;

public class robotnavigationDFS {

    private static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
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

        // Initialize minimum distance as a large value
        int[] minDist = {Integer.MAX_VALUE};

        // Start DFS from the starting position
        dfs(matrix, x1, y1, x2, y2, 0, visited, minDist);

        if (minDist[0] == Integer.MAX_VALUE) {
            // If the destination is not reachable
            System.out.println("No valid path found.");
            return -1;
        } else {
            System.out.println("Shortest path length: " + minDist[0]);
            return minDist[0];
        }
    }

    private static void dfs(int[][] matrix, int x, int y, int x2, int y2, int dist, boolean[][] visited, int[] minDist) {
        // Mark the current cell as visited
        visited[x][y] = true;

        // Check if the current point is the destination
        if (x == x2 && y == y2) {
            // Update the minimum distance
            minDist[0] = Math.min(minDist[0], dist);
            return;
        }

        // Define the directions (up, down, left, right)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Explore neighbors
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            // Check if the new position is valid and not visited
            if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length &&
                    matrix[newX][newY] == 0 && !visited[newX][newY]) {
                dfs(matrix, newX, newY, x2, y2, dist + 1, visited, minDist);
            }
        }

        // Backtrack: unmark the cell when backtracking
        visited[x][y] = false;
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
