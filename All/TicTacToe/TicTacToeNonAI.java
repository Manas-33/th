package ArtificialIntelligence.All.TicTacToe;


import java.util.Scanner;

public class TicTacToeNonAI {
    public static void main(String[] args) {
        char[][] board = {
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };

        int[] magicSquare = {2, 7, 6, 9, 5, 1, 4, 3, 8}; // Magic square for computer moves
        int currentPlayer = 1; // 1 for human, 2 for computer

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard(board);

            // making move conditions
            if (currentPlayer == 1) {
                System.out.print("Enter your move (1-9): ");
                int move = scanner.nextInt();
                if (!isValidMove(board, move)) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
                makeMove(board, move, 'X');
            } else {
                int move = findComputerMove(board, magicSquare);
                makeMove(board, move, 'O');
                System.out.println("Computer chooses " + move);
            }

            // Checking conditions for board
            if (isGameOver(board, 'X')) {
                printBoard(board);
                System.out.println("Congratulations! You win!");
                break;
            } else if (isGameOver(board, 'O')) {
                printBoard(board);
                System.out.println("Computer wins! Better luck next time.");
                break;
            } else if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = 3 - currentPlayer; // Switch players (1 <-> 2)
        }

        scanner.close();
    }

    // Printing the board
    public static void printBoard(char[][] board) {
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

    // Checking the valid move
    public static boolean isValidMove(char[][] board, int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        return board[row][col] != 'X' && board[row][col] != 'O';
    }

    // Changing the symbol for the respective move
    public static void makeMove(char[][] board, int move, char symbol) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        board[row][col] = symbol;
    }

    // checking the game over condition
    public static boolean isGameOver(char[][] board, char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }
        return false;
    }

    // checking the draw condition
    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    // finding the computer move using the magic square array
    public static int findComputerMove(char[][] board, int[] magicSquare) {
        for (int num : magicSquare) {
            if (isValidMove(board, num)) {
                return num;
            }
        }
        return -1; // No valid move found (shouldn't happen)
    }
}
