package models;

import java.awt.*;

/**
 * Knight chess piece class
 */
public class Knight extends Piece {

    public Knight(Color color, Square square) {
        super(color, square);
    }

    //The knight moves to any of the closest squares that are not on
    // the same rank, file, or diagonal, thus the move forms an "L"-shape
    // : two squares vertically and one square horizontally,
    // or two squares horizontally and one square vertically.
    // The knight is the only piece that can leap over other pieces.
    @Override
    public boolean validMove(Square currPos, Square destSquare, Board board) {
        if (!super.validMove(currPos, destSquare, board)) {
            return false;
        }

        int distRank = Math.abs(destSquare.getRank() - currPos.getRank());
        int distFile = Math.abs(destSquare.getFile() - currPos.getFile());
        if ((distRank == 2 && distFile == 1) || (distRank == 1 && distFile == 2)) {
            //Valid Knight movement pattern
            if (destSquare.occupyingPiece != null) {
                if (destSquare.occupyingPiece.getColor() != this.getColor()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

}
