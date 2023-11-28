package ArtificialIntelligence.All.TicTacToe;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Assignment1AI {
    private static int gameBoard[][] = new int[3][3];
    private List<GameMove> availableGameMoves;
    private static Scanner scanner = new Scanner(System.in);
    private GameMove computersGameMove;

    public List<GameMove> getAvailableGameMoves() {

        availableGameMoves = new ArrayList<GameMove>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == 0)
                    availableGameMoves.add(new GameMove(i, j));
            }
        }
        return availableGameMoves;
    }

    public void displayGameBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.print(" |");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + i + " " + j + ",");
            }
            System.out.println();
        }
    }

    private boolean isGameOver() {
        return has1Won() || has2Won() || getAvailableGameMoves().isEmpty();
    }

    private boolean has1Won() {

        if ((gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] == 1)
                || (gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] && gameBoard[0][2] == 1)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2] && gameBoard[i][0] == 1)
                    || (gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i] == gameBoard[2][i]
                    && gameBoard[0][i] == 1))) {
                return true;
            }
        }
        return false;
    }

    private boolean has2Won() {

        if ((gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] == 2)
                || (gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] && gameBoard[0][2] == 2)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2] && gameBoard[i][0] == 2)
                    || (gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i] == gameBoard[2][i]
                    && gameBoard[0][i] == 2)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Assignment1AI game = new Assignment1AI();
        Random random = new Random();
        game.displayGameBoard();
        System.out.println("");
        System.out.print("Who will play first? (C)omputer (H)uman ");
        char firstMove = scanner.next().charAt(0);

        if (firstMove == 'C') {
            GameMove gameMove = new GameMove(random.nextInt(3), random.nextInt(3));
            System.out.println("Computer will use 1, Human will use 2\n");
            game.makeGameMove(gameMove, 1);
            game.displayGameBoard();
        }

        while (!game.isGameOver()) {
            System.out.print("Human Move: ");
            GameMove gameMove = new GameMove(scanner.nextInt(), scanner.nextInt());
            game.makeGameMove(gameMove, 2);
            game.displayGameBoard();

            if (game.isGameOver())
                break;

            game.minimax(0, 1);

            game.makeGameMove(game.computersGameMove, 1);
            game.displayGameBoard();

        }

        if (game.has1Won())
            System.out.println("You Lose!");
        else if (game.has2Won())
            System.out.println("You Win..."); // never lose!
        else
            System.out.println("Draw Match!");
    }

    public int minimax(int depth, int turn) {

        if (has1Won())
            return +1;
        if (has2Won())
            return -1;

        List<GameMove> gameMovesAvailable = getAvailableGameMoves();
        if (gameMovesAvailable.isEmpty())
            return 0;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < gameMovesAvailable.size(); ++i) {

            GameMove gameMove = gameMovesAvailable.get(i);

            if (turn == 1) {
                makeGameMove(gameMove, 1);
                int currentScore = minimax(depth + 1, 2);
                max = Math.max(currentScore, max);

                if (depth == 0)
                    System.out.println("info: score for position " + (i+1) + ": " + currentScore);
                if (currentScore >= 0) {
                    if (depth == 0)
                        computersGameMove = gameMove;
                } else if (currentScore == 1) {
                    resetGameMove(gameMove);
                    break;
                }
                if (i == availableGameMoves.size() - 1 && max > 0) {    
                    if (depth == 0)
                        computersGameMove = gameMove;
                }
            } else if (turn == 2) {
                makeGameMove(gameMove, 2);
                int currentScore = minimax(depth + 1, 1);
                min = Math.min(currentScore, min);
                if (min == -1) {
                    resetGameMove(gameMove);
                    break;
                }
            }
            resetGameMove(gameMove);
        }
        return (turn == 1) ? max : min;
    }

    public void resetGameMove(GameMove move) {
        gameBoard[move.getX()][move.getY()] = 0;
    }

    public void makeGameMove(GameMove move, int player) {

        gameBoard[move.getX()][move.getY()] = player;
    }

}

class GameMove {

    private int x;
    private int y;

    public GameMove(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}