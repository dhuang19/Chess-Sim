package models;

import interfaces.ISquare;

import java.awt.*;

/**
 * Class for a square on the chess board
 */
public class Square implements ISquare {

    /**
     * Color of this square
     */
    private Color _color;

    /**
     * Rank (row index) of this square
     */
    private int _rank;

    /**
     * File (column index) of this square
     */
    private int _file;

    /**
     * Chess piece occupying this square
     */
    public Piece occupyingPiece;

    @Override
    public int getFile() {
        return _file;
    }

    @Override
    public int getRank() {
        return _rank;
    }

    public Square(int colIdx, int rowIdx) {
        _rank = rowIdx;
        _file = colIdx;
        occupyingPiece = null;

        //assign color
        if ((colIdx + rowIdx) % 2 == 0) {
            _color = Color.black;
        } else {
            _color = Color.white;
        }
    }


}
