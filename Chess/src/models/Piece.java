package models;

import utilities.BoardUtilities;

import java.awt.*;
import java.util.ArrayList;

/**
 * Base class for a chess piece
 */
public abstract class Piece {
    /**
     * Square this chess piece is on
     */
    //TODO: refactor all methods that take in "currPos" as arg
    public Square currentPos;

    /**
     * Color of this piece
     */
    private Color _color;

    public Piece(Color color, Square square) {
        _color = color;
        currentPos = square;
    }

    /**
     * Getter for color
     * @return
     * Color of this piece
     */
    public Color getColor() {
        return _color;
    }

    /**
     * Checks if a move is valid or not
     * @param currPos
     * Current position of piece
     * @param destSquare
     * Destination of piece
     * @param board
     * Chess board
     * @return
     * True if move is valid and is done, False if invalid move
     */
    public boolean validMove(Square currPos, Square destSquare, Board board) {
        //Check destination and current position is within bounds
        if (!BoardUtilities.withinBoardBounds(destSquare.getRank(), destSquare.getFile(), board)
            || !BoardUtilities.withinBoardBounds(currPos.getRank(), currPos.getFile(), board)) {
            return false;
        }
        return true;
    }

    /**
     * Make the move from curr position to destination
     * @param currPos
     * Current position
     * @param destSquare
     * Destination
     */
    protected void makeMove(Square currPos, Square destSquare) {
        if (destSquare.occupyingPiece != null) {
            System.out.println("captured piece!");
        }
        currPos.occupyingPiece = null;
        destSquare.occupyingPiece = this;
        this.currentPos = destSquare;
    }

}
