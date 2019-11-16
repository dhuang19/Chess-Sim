package views;

import models.Board;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * JFrame view for chess board
 * CREDITS: Some parts taken from ANDREW THOMPSON'S ANSWER ON
 * https://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
 */
public class ChessView implements ActionListener{

    /**
     * Chess board to visualize
     */
    private Board _board = new Board();

    /**
     * JPanel parent
     */
    private final JPanel _chessGUI = new JPanel(new BorderLayout(3,3));

    /**
     * JButtons for player interaction
     */
    private JButton[][] _squares = new JButton[8][8];

    /**
     * JPanel chess board
     */
    private JPanel _chessBoard;

    /**
     * Letters representing Columns
     */
    private static final String _cols = "ABCDEFGH";

    /**
     * Unicode char for white pawn
     */
    private static final String _pawnWhite = "\u2659";

    /**
     * Unicode char for black pawn
     */
    private static final String _pawnBlack = "\u265F";

    /**
     * Unicode chars for white pieces back row
     */
    private static final String[] _piecesWhite = {
            "\u2656",
            "\u2658",
            "\u2657",
            "\u2655",
            "\u2654",
            "\u2657",
            "\u2658",
            "\u2656"
    };

    /**
     * Unicode chars for black pieces back row
     */
    private static final String[] _piecesBlack = {
            "\u265C",
            "\u265E",
            "\u265D",
            "\u265B",
            "\u265A",
            "\u265D",
            "\u265E",
            "\u265C"
    };

    /**
     * Font to use for unicode chars
     */
    private static final Font font = new Font("Sans-Serif", Font.PLAIN, 64);

    public ChessView(Board board) {
        //Initialize vars based on board
        _board = board;
        _squares = new JButton[_board.getFileCount()][_board.getRankCount()];

        initGUI();
    }

    /**
     * Initializes the JPanel for chess GUI
     */
    private final void initGUI() {
        _chessGUI.setBorder(new EmptyBorder(5,5,5,5));

        _chessBoard = new JPanel(new GridLayout(0,_board.getRankCount() + 1));
        _chessBoard.setBorder(new LineBorder(Color.black));
        _chessGUI.add(_chessBoard);

        //Create Squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < _squares.length; ii++) {
            for (int jj = 0; jj < _squares[ii].length; jj++) {
                //Fill squares with chess pieces
                JButton b = new JButton();
                if (ii == 0) {
                    b = new JButton(_piecesBlack[jj]);
                } else if (ii == 7) {
                    b = new JButton(_piecesWhite[jj]);
                } else if (ii == 1) {
                    b = new JButton(_pawnBlack);
                } else if (ii == 6) {
                    b = new JButton(_pawnWhite);
                } else if (ii == 2){
                    if (jj == 3) {
                        //Add Princess
                        b = new JButton("P");
                    } else if (jj == 4) {
                        //Add Barry
                        b = new JButton("B");
                    }
                } else if (ii == 5) {
                    if (jj == 3) {
                        //Add Princess
                        b = new JButton("P");
                        b.setForeground(Color.lightGray);
                    } else if (jj == 4) {
                        //Add Barry
                        b = new JButton("B");
                        b.setForeground(Color.lightGray);
                    }
                }

                b.setFont(font);
                b.setMargin(buttonMargin);
                b.setPreferredSize(new Dimension(110,110));
                b.setHorizontalTextPosition(SwingConstants.CENTER);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);

                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                _squares[jj][ii] = b;
            }
        }

        //fill the chess board
        _chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            _chessBoard.add(
                    new JLabel(_cols.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < _board.getRankCount(); ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        _chessBoard.add(new JLabel("" + (_board.getRankCount() - ii),
                                SwingConstants.CENTER));
                    default:
                        _chessBoard.add(_squares[jj][ii]);
                }
            }
        }
    }

    /**
     * Retrieve Chess GUI
     * @return
     * Chess GUI
     */
    public final JComponent getGUI() {
        return _chessGUI;
    }

    /**
     * Retrives Chess JFrame with configurations
     * @return
     * Chess Frame
     */
    public JFrame getFrame() {
        JFrame frame = new JFrame("Chess");
        frame.add(this.getGUI());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        //ensure all components are displayed
        frame.pack();
        frame.setSize(frame.getSize());

        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
                "I was clicked by "+e.getActionCommand(),
                "Title here", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        Board board = new Board();
        ChessView cv = new ChessView(board);

        JFrame frame = cv.getFrame();
        frame.setVisible(true);
    }
}