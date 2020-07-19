import javax.swing.*;
import java.util.Arrays;

public class Board {

    public static final int BOARD_SIZE = 3;

    private int[][] board;
    private int currentPlayer;
    private int winner;
    private int movesDone;
    private int movesAvailable;
    private boolean gameOver;

    public Board(){
        newGame();
    }

    public boolean move(int row, int col){
        if (gameOver){
            throw new IllegalThreadStateException("Game is over");
        }

        if (board[row][col] == 0){
            board[row][col] = currentPlayer;
        }
        else {
            return false;
        }

        movesDone ++;
        movesAvailable --;

        if (movesAvailable == 0){
            // Game ends with winner = 0, noone has won
            gameOver = true;
        }
        checkWinner(row, col);
        changePlayer();
        return true;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean canMoveHere(int row, int col){
        return board[row][col] == 0;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getMovesAvailable() {
        return movesAvailable;
    }

    public int getWinner(){
        if (!gameOver){
            throw new IllegalStateException("No winner yet");
        }
        return winner;
    }

    public void changePlayer(){
        if (currentPlayer == 1){
            currentPlayer = 2;
        }
        else if (currentPlayer == 2){
            currentPlayer = 1;
        }
        else{
            throw new IllegalStateException("Invalid player number");
        }
    }

    public void newGame(){
        board = new int[BOARD_SIZE][BOARD_SIZE];
        movesAvailable = BOARD_SIZE * BOARD_SIZE;
        movesDone = 0;
        gameOver = false;
        currentPlayer = 1;
        winner = 0;
    }

    private void checkWinner(int row, int col){
        checkRow(row);
        checkCol(col);
        checkDiagonal(row, col);
        checkAntiDiagonal(row, col);
    }

    private void checkRow(int row){
        int numInRow = 0;
        for (int i = 0; i < BOARD_SIZE; i ++){
            if (board[row][i] == currentPlayer){
                numInRow ++;
            }
        }
        if (numInRow == BOARD_SIZE){
            winner = currentPlayer;
            gameOver = true;
        }
    }

    private void checkCol(int col){
        int numInCol = 0;
        for (int i = 0; i < BOARD_SIZE; i ++){
            if (board[i][col] == currentPlayer){
                numInCol ++;
            }
        }
        if (numInCol == BOARD_SIZE){
            winner = currentPlayer;
            gameOver = true;
        }
    }

    private void checkDiagonal(int row, int col){
        if (row == col) {
            int numInDiag = 0;
            for (int i = 0; i < BOARD_SIZE; i ++){
                if (board[i][i] == currentPlayer){
                    numInDiag ++;
                }
            }
            if (numInDiag == BOARD_SIZE){
                winner = currentPlayer;
                gameOver = true;
            }
        }
    }

    private void checkAntiDiagonal(int row, int col){
        if (row + col == BOARD_SIZE - 1){
            int numInDiag = 0;
            for (int i = 0; i < BOARD_SIZE; i ++){
                if (board[i][BOARD_SIZE -1 - i] == currentPlayer){
                    numInDiag ++;
                }
            }
            if (numInDiag == BOARD_SIZE){
                winner = currentPlayer;
                gameOver = true;
            }
        }
    }

    public int[][] getBoard(){
        return this.board;
    }

    public Board deepCopy(){
        Board copy = new Board();

        for (int i = 0; i < BOARD_SIZE; i ++){
            copy.board[i] = Arrays.copyOf(this.board[i], BOARD_SIZE);
        }
        copy.currentPlayer = this.currentPlayer;
        copy.winner = this.winner;
        copy.movesDone = this.movesDone;
        copy.movesAvailable = this.movesAvailable;
        copy.gameOver = this.gameOver;
        return copy;
    }

}
