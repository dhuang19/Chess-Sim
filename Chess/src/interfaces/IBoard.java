package interfaces;

import models.King;
import models.Piece;
import models.Square;

import java.awt.*;

/**
 * Interface for Chess Board
 */
public interface IBoard {
    /**
     * Get rank dimensions
     * @return
     * ranks (number of rows)
     */
    int getRankCount();

    /**
     * Get file dimensions
     * @return
     * files (number of columns
     */
    int getFileCount();

    /**
     * Gets Square object at coordinates
     * @param fileIdx
     * File index (x coordinate)
     * @param rankIdx
     * Rank index (y coordinate)
     * @return
     * Square at those coordinates
     */
    Square getSquareAt(int fileIdx, int rankIdx);

    /**
     * Places a chess piece at coordinates
     * @param fileIdx
     * File index (x coordinate)
     * @param rankIdx
     * Rank index (y coordinate)
     * @param piece
     * Piece to place at coordinates
     */
    void placePieceAt(int fileIdx, int rankIdx, Piece piece);

    /**
     * Places a chess piece at square
     * @param place
     * Square to place piece on
     * @param piece
     * Piece to place at square
     */
    void placePieceAt(Square place, Piece piece);

    /**
     * Called by player when they want to make a play
     * @param currPos
     * Current square piece to move is on
     * @param destSquare
     * Square to move piece to
     * @return
     * Whether play was valid
     */
    boolean playRequested(Square currPos, Square destSquare);

}
