package utilities;

import models.Board;
import models.King;
import models.Square;

import java.awt.*;
import java.util.ArrayList;

/**
 * Utilities for calculating plays
 */
public final class BoardUtilities {

    /**
     * Evaluate if position is within board dimensions
     * @param rankIdx
     * Rank index
     * @param fileIdx
     * File index
     * @param board
     * Board to evaluate position on
     * @return
     * If position is within the board
     */
    public static boolean withinBoardBounds(int rankIdx, int fileIdx, Board board) {
        if (rankIdx >= 0 && rankIdx <= board.getRankCount() &&
            fileIdx >= 0 && fileIdx <= board.getFileCount()) {
            return true;
        }
        return false;
    }

    /**
     * Detects if there is a piece in the way when moving along rank
     * @param origin
     * Square that it starts on
     * @param dest
     * Destination square
     * @return
     * Square that blocking piece is on; null if nothing there
     */
    public static Square pieceInWayRank(Square origin, Square dest, Board board) {
        int dist = origin.getFile() - dest.getFile();
        Square current = origin;
        int increment = 0;
        if (dist > 0) {
            //Moving from right to left
            increment = -1;
        } else {
            //Moving from left to right
            increment = 1;
        }
        for (int i = 0; i < Math.abs(dist); i++) {
            current = board.getSquareAt(current.getFile()+increment, current.getRank());
            if (current.occupyingPiece != null) {
                return current;
            }
        }
        return null;
    }

    /**
     * Detects if there is a piece in the way when movng along file
     * @param origin
     * Square that it starts on
     * @param dest
     * Destination square
     * @return
     * Square that blocking piece is on; null if nothing there
     */
    public static Square pieceInWayFile(Square origin, Square dest, Board board) {
        int dist = dest.getRank() - origin.getRank();
        Square current = origin;
        int increment = 0;
        if (dist > 0) {
            //Moving bottom to top
            increment = 1;
        } else {
            //Moving top to bottom
            increment = -1;
        }
        for (int i = 0; i < Math.abs(dist); i++) {
            current = board.getSquareAt(current.getFile(), current.getRank()+increment);
            if (current.occupyingPiece != null) {
                return current;
            }
        }
        return null;
    }

    /**
     * Detects if there is a piece in the way in path from bottom left square to top right square
     * @param origin
     * Square that it starts on
     * @param dest
     * Destination square
     * @param board
     * Chess board
     * @return
     * Square that blocking piece is on; null if nothing there
     */
    public static Square pieceInWayDiagonal_BL_TR(Square origin, Square dest, Board board) {
        if (!pathIsDiagonal(origin, dest)) {
            return null;
        }

        int dist = dest.getFile() - origin.getFile();
        Square current = origin;
        int increment = 0;
        if (dist > 0) {
            //Moving from BL -> TR
            increment = 1;
        } else {
            //Moving from TR -> BL
            increment = -1;
        }

        for (int i = 0; i < Math.abs(dist); i++) {
            current = board.getSquareAt(current.getFile()+increment, current.getRank()+increment);
            if (current.occupyingPiece != null) {
                return current;
            }
        }
        return null;
    }

    /**
     * Detects if there is a piece in the way from bottom right to top left square
     * @param origin
     * Square that it's starting on
     * @param dest
     * Destination square
     * @param board
     * Chess board
     * @return
     * Square that blocking piece is on; null if nothing there
     */
    public static Square pieceInWayDiagonal_BR_TL(Square origin, Square dest, Board board) {
        if (!pathIsDiagonal(origin, dest)) {
            return null;
        }

        int dist = dest.getFile() - origin.getFile();
        Square current = origin;
        int rankIncrement = 0;
        int fileIncrement = 0;
        if (dist > 0) {
            //Moving from TL -> BR
            rankIncrement = -1;
            fileIncrement = 1;
        } else {
            //Moving from BR -> TL
            rankIncrement = 1;
            fileIncrement = -1;
        }

        for (int i = 0; i < Math.abs(dist); i++) {
            current = board.getSquareAt(current.getFile()+fileIncrement, current.getRank()+rankIncrement);
            if (current.occupyingPiece != null) {
                return current;
            }
        }
        return null;
    }

    /**
     * Calculates slope
     * @param curr
     * Current point
     * @param dest
     * Destination point
     * @return
     * Slope
     */
    public static float calculateSlope(Square curr, Square dest) {
        return ((float) (dest.getRank() - curr.getRank())) / ((float) (dest.getFile() - curr.getRank()));
    }

    /**
     * Tests if path is diagonal
     * @param currPos
     * Current square
     * @param destSquare
     * Destination square
     * @return
     * Boolean on if the path is diagonal
     */
    public static boolean pathIsDiagonal (Square currPos, Square destSquare) {
        if (Math.abs(currPos.getRank()-destSquare.getRank())
                == Math.abs(currPos.getFile() - destSquare.getFile())) {
            return true;
        }
        return false;
    }

    /**
     * Gets all active pieces belonging to team color param
     * King piece gets placed at index 0
     * @param color
     * Team color
     * @param board
     * Chess board
     * @return
     * ArrayList of squares those pieces are on
     */
    public static ArrayList<Square> getAllActivePieces(Color color, Board board) {
        ArrayList<Square> ret = new ArrayList<Square>();
        Square sq = null;

        for (int row = 0; row < board.getRankCount(); row++ ) {
            for (int col = 0; col < board.getFileCount(); col++) {
                sq = board.getSquareAt(col, row);
                if (sq != null && sq.occupyingPiece != null && sq.occupyingPiece.getColor() == color) {
                    if (sq.occupyingPiece instanceof King) {
                        ret.add(0, sq);
                    } else {
                        ret.add(sq);
                    }
                }
            }
        }

        return ret;
    }


}
