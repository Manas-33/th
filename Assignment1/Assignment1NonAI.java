package ArtificialIntelligence.Assignment1;
//import java.util.Scanner;
//
//public class Assignment1NonAI {
//    private static final int[][] MAGIC_SQUARE = {
//            {2, 9, 4},
//            {7, 5, 3},
//            {6, 1, 8}
//    };
//
//    private static final char[][] board = {
//            {'1', '2', '3'},
//            {'4', '5', '6'},
//            {'7', '8', '9'}
//    };
//
//    private static char currentPlayer = 'X';
//    private static int moves = 0;
//
//    public static void main(String[] args) {
//        playGame();
//    }
//
//    private static void displayBoard() {
//        System.out.println("-------------");
//        for (int i = 0; i < 3; i++) {
//            System.out.print("| ");
//            for (int j = 0; j < 3; j++) {
//                System.out.print(board[i][j] + " | ");
//            }
//            System.out.println();
//            System.out.println("-------------");
//        }
//    }
//
//    private static boolean isValidMove(int move) {
//        int row = (move - 1) / 3;
//        int col = (move - 1) % 3;
//        return board[row][col] != 'X' && board[row][col] != 'O';
//    }
//
//    private static boolean checkWin() {
//        int[] playerMoves = new int[9];
//        int index = 0;
//
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board[i][j] == currentPlayer) {
//                    playerMoves[index++] = MAGIC_SQUARE[i][j];
//                }
//            }
//        }
//
//
//        for (int i = 0; i < 3; i++) {
//            if (playerMoves[i] + playerMoves[i + 3] + playerMoves[i + 6] == 15) {
//                return true;
//            }
//            if (playerMoves[i * 3] + playerMoves[i * 3 + 1] + playerMoves[i * 3 + 2] == 15) {
//                return true;
//            }
//        }
//
//        if (playerMoves[0] + playerMoves[4] + playerMoves[8] == 15) {
//            return true;
//        }
//        return playerMoves[2] + playerMoves[4] + playerMoves[6] == 15;
//    }
//
//    private static boolean isGameOver() {
//        return moves >= 9 || checkWin();
//    }
//
//    private static void playGame() {
//        Scanner scanner = new Scanner(System.in);
//        displayBoard();
//
//        while (!isGameOver()) {
//            System.out.println("Player " + currentPlayer + ", enter your move (1-9):");
//            int move = scanner.nextInt();
//
//            if (move < 1 || move > 9) {
//                System.out.println("Invalid move. Please enter a number between 1 and 9.");
//                continue;
//            }
//
//            if (!isValidMove(move)) {
//                System.out.println("Invalid move. Cell already taken. Try again.");
//                continue;
//            }
//
//            int row = (move - 1) / 3;
//            int col = (move - 1) % 3;
//            board[row][col] = currentPlayer;
//
//            moves++;
//            displayBoard();
//
//            if (checkWin()) {
//                System.out.println("Player " + currentPlayer + " wins!");
//                break;
//            }
//
//            if (moves == 9) {
//                System.out.println("It's a draw!");
//                break;
//            }
//
//            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
//        }
//
//        scanner.close();
//    }
//
//}
//
import java.util.Random;
import java.util.Scanner;

public class Assignment1NonAI {
    private static final int[][] MAGIC_SQUARE = {
            {2, 9, 4},
            {7, 5, 3},
            {6, 1, 8}
    };

    private static final char[][] board = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
    };

    private static char currentPlayer = 'X';
    private static int moves = 0;

    public static void main(String[] args) {
        playGame();
    }

    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static boolean isValidMove(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        return board[row][col] != 'X' && board[row][col] != 'O';
    }

    private static boolean checkWin(char player) {
        int[] playerMoves = new int[9];
        int index = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == player) {
                    playerMoves[index++] = MAGIC_SQUARE[i][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (playerMoves[i] + playerMoves[i + 3] + playerMoves[i + 6] == 15) {
                return true;
            }
            if (playerMoves[i * 3] + playerMoves[i * 3 + 1] + playerMoves[i * 3 + 2] == 15) {
                return true;
            }
        }

        return playerMoves[0] + playerMoves[4] + playerMoves[8] == 15 || playerMoves[2] + playerMoves[4] + playerMoves[6] == 15;
    }

    private static boolean isGameOver() {
        return moves >= 9 || checkWin('X') || checkWin('O');
    }

    private static void makeMove(int move, char player) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        board[row][col] = player;
        moves++;
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        displayBoard();

        while (!isGameOver()) {
            if (currentPlayer == 'X') {
                System.out.println("Player " + currentPlayer + ", enter your move (1-9):");
                int move = scanner.nextInt();

                if (move < 1 || move > 9) {
                    System.out.println("Invalid move. Please enter a number between 1 and 9.");
                    continue;
                }

                if (!isValidMove(move)) {
                    System.out.println("Invalid move. Cell already taken. Try again.");
                    continue;
                }

                makeMove(move, currentPlayer);
            } else {
                System.out.println("AI is making a move...");
                makeAIPlayerMove();
            }

            displayBoard();

            if (checkWin(currentPlayer)) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (moves == 9) {
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }

    private static void makeAIPlayerMove() {
        Random rand = new Random();
        int move;
        do {
            move = rand.nextInt(9) + 1;
        } while (!isValidMove(move));
        makeMove(move, 'O');
    }
}
