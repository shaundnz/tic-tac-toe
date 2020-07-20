import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardController {

    private BoardView boardView;
    private Board boardModel;

    public BoardController(BoardView boardView, Board boardModel){
        this.boardView = boardView;
        this.boardModel = boardModel;
        this.boardView.addBoardPanelMouseListener(new BoardListener());
    }

    class BoardListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            int i = e.getY() * Board.BOARD_SIZE / BoardView.BOARD_DIMENSION;
            int j = e.getX() * Board.BOARD_SIZE / BoardView.BOARD_DIMENSION;
            // Player makes move, then checks for winner
            if (!boardModel.isGameOver()) {
                boolean playerMove = boardModel.move(i, j);
                updateAndRepaintBoard();
                if (playerMove && !boardModel.isGameOver()) {
                    // AI Makes move
                    MiniMax.miniMax(boardModel, 0, true);
                    updateAndRepaintBoard();
                    endGameScreen();
                }
                else{
                    endGameScreen();
                }
            }
        }

        private void endGameScreen(){
            if (boardModel.isGameOver()){
                int winner = boardModel.getWinner();
                String message;
                switch (winner){
                    case 0:
                        // Draw
                        message = "Its a draw!";
                        break;
                    case 1:
                        // Player 1 wins
                        message = "Player One has won!";
                        break;
                    case 2:
                        // AI Wins
                        message = "Player Two has won!";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected winner value: " + winner);
                }
                int option = boardView.showJOptionPanel(message);
                if (option == 0){
                    boardModel.newGame();
                    updateAndRepaintBoard();
                }
                else if (option == 1){
                    System.exit(1);
                }
                else {
                    throw new IllegalStateException("Unexpected option value: " + option);
                }
            }
        }
    }

    public void updateAndRepaintBoard(){
        boardView.getBoardPanel().updateBoard(boardModel.getBoard());
        boardView.getBoardPanel().repaint();
    }

    public static void main(String[] args){
        BoardView  boardView = new BoardView();
        Board boardModel = new Board();
        BoardController boardController = new BoardController(boardView, boardModel);
    }
}
