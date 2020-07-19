import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class BoardView extends JFrame {

    private JLabel displayTurn;
    private BoardPanel boardPanel;
    public static final int BOARD_DIMENSION = 600;

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
            this.board = new int[Board.BOARD_SIZE][Board.BOARD_SIZE];
            setPreferredSize(new Dimension(BOARD_DIMENSION, BOARD_DIMENSION));
        }

        public void updateBoard(int[][] updatedBoard){
            board = updatedBoard;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            paintGrid(g);
            paintSquares(g);
        }

        private void paintGrid(Graphics g){
            g.setColor(Color.lightGray);
            // Draw rows;
            int offset = BOARD_DIMENSION / Board.BOARD_SIZE;
            int y = offset;
            for (int i = 0; i < Board.BOARD_SIZE - 1; i ++){
                g.fillRoundRect(10, y, BOARD_DIMENSION - 20, 10, 5, 5);
                y += offset;
            }
            // Draw columns
            int x = offset;
            for (int i = 0; i < Board.BOARD_SIZE - 1; i ++){
                g.fillRoundRect(x, 10, 10, BOARD_DIMENSION - 20, 5, 5);
                x += offset;
            }
        }

        private void paintSquares(Graphics g){
            int offset = BOARD_DIMENSION / Board.BOARD_SIZE;
            for (int row = 0; row < Board.BOARD_SIZE; row ++){
                for (int col = 0; col < Board.BOARD_SIZE; col ++){
                    if (board[row][col] == 1){
                        g.setColor(Color.RED);
                        g.fillOval(col * offset + 10, row * offset + 10, offset - 20, offset - 20);
                    }
                    else if (board[row][col] == 2){
                        g.setColor(Color.BLUE);
                        g.fillOval(col * offset + 10, row * offset + 10, offset - 20, offset - 20);
                    }
                }
            }
        }

    }

    public BoardPanel getBoardPanel(){
        return this.boardPanel;
    }

    public void addBoardPanelMouseListener(MouseAdapter mouseAdapter){
        boardPanel.addMouseListener(mouseAdapter);
    }

    public void updateTurnLabel(String message){
        displayTurn.setText(message);
    }

    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
