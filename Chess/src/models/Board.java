package models;

import interfaces.IBoard;
import utilities.BoardUtilities;
import utilities.DefaultChessValues;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class for a chess board
 */
public class Board implements IBoard {
    /**
     * Chess board representation
     */
    private Square[][] _board;

    /**
     * Number of ranks on board (rows)
     */
    private int _ranksCount;

    /**
     * Number of files on board (columns)
     */
    private int _filesCount;

    @Override
    public Square getSquareAt(int fileIdx, int rankIdx) {
        if (!BoardUtilities.withinBoardBounds(rankIdx, fileIdx, this)) {
            //throw new IndexOutOfBoundsException("Indices provided do not exist on board");
            return null;
        } else {
            return _board[rankIdx][fileIdx];
        }
    }

    @Override
    public void placePieceAt(int fileIdx, int rankIdx, Piece piece) {
        placePieceAt(getSquareAt(fileIdx,rankIdx), piece);
    }

    @Override
    public void placePieceAt(Square place, Piece piece) {
        place.occupyingPiece = piece;
        piece.currentPos = place;
    }

    @Override
    public boolean playRequested(Square currPos, Square destSquare) {
        if (!BoardUtilities.withinBoardBounds(destSquare.getRank(), destSquare.getFile(), this)) {
            throw new IndexOutOfBoundsException("Cannot move to a position off of board.");
        }
        if (currPos.occupyingPiece == null) {
            throw new IllegalArgumentException("Square does not contain a chess piece.");
        }

        Color playerColor = currPos.occupyingPiece.getColor();
        boolean valid = currPos.occupyingPiece.validMove(currPos, destSquare, this);
        if (!valid) {
            return false;
        } else {
            //Make move
            currPos.occupyingPiece.makeMove(currPos, destSquare);

            //Check for checkmate
            King king = isCheckmated(playerColor);
            if (king != null) {
                System.out.println("King of " + king.getColor().toString() + " player is checkmated!");
                System.out.println(("Player " + playerColor.toString() + " wins!"));
            }
            return true;
        }
    }

    public int getFileCount() {
        return _filesCount;
    }

    @Override
    public int getRankCount() {
        return _ranksCount;
    }

    public Board(int ranks, int files) {
        this.setDimensions(ranks, files);
        _board = new Square[ranks][files];
        initializeSquares(ranks, files);
    }

    public Board() {
        this.setDimensions(DefaultChessValues._defaultSize, DefaultChessValues._defaultSize);
        _board = new Square[DefaultChessValues._defaultSize][DefaultChessValues._defaultSize];
        initializeSquares(DefaultChessValues._defaultSize, DefaultChessValues._defaultSize);
    }

    /**
     * Initializes square objects on board
     * @param ranks
     * number of ranks (rows)
     * @param files
     * number of files (columns)
     */
    private void initializeSquares(int ranks, int files) {
        for (int row = 0; row < ranks; row++ ) {
            for (int col = 0; col < files; col++) {
                _board[row][col] = new Square(col, row);
            }
        }
    }

    /**
     * Validates and sets values for ranks and files on board
     * @param ranks
     * number of ranks (rows)
     * @param files
     * number of files (columns)
     */
    private void setDimensions(int ranks, int files) {
        if (ranks <= 0 || files <= 0) {
            throw new IllegalArgumentException("Dimensions of board must be greater than 0.");
        } else {
            _ranksCount = ranks;
            _filesCount = files;
        }
    }

    /**
     * Checks the board state to see if a King is checkmated
     * @param turnColor
     * Color of whichever player is moving this turn
     * @return
     * The checkmated king; null if no checkmate
     */
    private King isCheckmated(Color turnColor) {
        Color opponent;
        if (turnColor == DefaultChessValues._colorPlayer1) {
            opponent = DefaultChessValues._colorPlayer2;
        } else {
            opponent = DefaultChessValues._colorPlayer1;
        }
        //Get all pieces of opponent Color
        ArrayList<Square> opponentTurnPieces = BoardUtilities.getAllActivePieces(opponent, this);

        //For all turncolor pieces on board, check if move to opposingColor piece is valid
        ArrayList<Square> turnPieces = BoardUtilities.getAllActivePieces(turnColor, this);
        for (Square sq : turnPieces) {
            boolean canCapture = sq.occupyingPiece.validMove(sq, opponentTurnPieces.get(0), this);
            if (canCapture) {
                return (King) opponentTurnPieces.get(0).occupyingPiece;
            }
        }

        return null;
    }

}
