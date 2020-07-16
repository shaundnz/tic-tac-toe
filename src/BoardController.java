public class BoardController {

    private BoardView boardView;
    private BoardModel boardModel;

    public BoardController(BoardView boardView, BoardModel boardModel){
        this.boardView = boardView;
        this.boardModel = boardModel;
    }
}
