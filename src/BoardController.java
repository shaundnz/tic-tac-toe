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
                boardView.getBoardPanel().updateBoard(boardModel.getBoard());
                boardView.getBoardPanel().repaint();
                System.out.println(boardModel.isGameOver());
                if (playerMove && !boardModel.isGameOver()){
                    // AI Makes move
                    MiniMax.miniMax(boardModel, 0, true);
                    boardView.getBoardPanel().updateBoard(boardModel.getBoard());
                    boardView.getBoardPanel().repaint();
                }
            }
        }
    }

    public static void main(String[] args){
        BoardView  boardView = new BoardView();
        Board boardModel = new Board();
        BoardController boardController = new BoardController(boardView, boardModel);
    }
}
