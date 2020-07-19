public class MiniMax {


    public static int miniMax(Board board, int depth, boolean isMaximising){
        if (board.isGameOver() || board.getMovesAvailable() == 0){
            // Game is draw
            if (board.getWinner() == 0){
                return 0;
            }
            // AI has won
            else if (board.getWinner() == 2){
                return 1;
            }
            // Player has won
            else if (board.getWinner() == 1){
                return -1;
            }
        }

        if (isMaximising){
            double bestScore = Double.NEGATIVE_INFINITY;
            int[] bestMove = new int[2];
            for (int i = 0; i < Board.BOARD_SIZE; i++){
                for (int j = 0; j < Board.BOARD_SIZE; j ++){
                    Board copyBoard = board.deepCopy();
                    if (copyBoard.canMoveHere(i, j)) {
                        copyBoard.move(i, j);
                        int score = miniMax(copyBoard, depth + 1, false);
                        if (score >= bestScore) {
                            bestScore = score;
                            bestMove[0] = i;
                            bestMove[1] = j;
                        }
                    }
                }
            }
            board.move(bestMove[0], bestMove[1]);
            return (int) bestScore;
        }
        else {
            double bestScore = Double.POSITIVE_INFINITY;
            int[] bestMove = new int[2];
            for (int i = 0; i < Board.BOARD_SIZE; i++) {
                for (int j = 0; j < Board.BOARD_SIZE; j++) {
                    Board copyBoard = board.deepCopy();
                    if (copyBoard.canMoveHere(i, j)) {
                        copyBoard.move(i, j);
                        int score = miniMax(copyBoard, depth + 1, true);
                        if (score <= bestScore) {
                            bestScore = score;
                            bestMove[0] = i;
                            bestMove[1] = j;
                        }
                    }
                }
            }
            board.move(bestMove[0], bestMove[1]);
            return (int) bestScore;
        }
    }
}
