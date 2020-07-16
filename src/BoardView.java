import javax.swing.*;
import java.awt.*;

public class BoardView extends JFrame {

    private JLabel displayTurn;
    private BoardPanel boardPanel;
    private final int boardDimension = 600;

    public BoardView(){
        createGUI();
    }

    private void createGUI(){
        setTitle("Tic Tac Toe");
        setLayout(new BorderLayout());

        displayTurn = new JLabel("Player 1's Turn");
        displayTurn.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        boardPanel = new BoardPanel();
        add(displayTurn, BorderLayout.PAGE_START);
        add(boardPanel, BorderLayout.CENTER);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    class BoardPanel extends JPanel{

        int[][] board;

        public BoardPanel(){
            this.board = new int[BoardModel.BOARD_SIZE][BoardModel.BOARD_SIZE];
            setPreferredSize(new Dimension(boardDimension, boardDimension));
        }

        public void updateBoard(int[][] updatedBoard){
            board = updatedBoard;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            paintGrid(g);
        }

        private void paintGrid(Graphics g){
            g.setColor(Color.lightGray);
            // Draw rows;
            int offset = boardDimension / BoardModel.BOARD_SIZE;
            int y = offset;
            for (int i = 0; i < BoardModel.BOARD_SIZE - 1; i ++){
                g.fillRoundRect(10, y, boardDimension - 20, 10, 5, 5);
                y += offset;
            }
            // Draw columns
            int x = offset;
            for (int i = 0; i < BoardModel.BOARD_SIZE - 1; i ++){
                g.fillRoundRect(x, 10, 10, boardDimension - 20, 5, 5);
                x += offset;
            }
        }
    }

    public void updateTurnLabel(String message){
        displayTurn.setText(message);
    }

}
