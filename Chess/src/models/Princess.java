package models;

import utilities.BoardUtilities;

import java.awt.*;

/**
 * Princess chess piece
 * Princess starts in front of the pawn in front of the Queen
 * It can move any number of squares along file, but may not jump over pieces.
 */
public class Princess extends Piece {

    public Princess(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean validMove(Square currPos, Square destSquare, Board board) {
        if (!super.validMove(currPos, destSquare, board)) {
            return false;
        }

        Square res = null;
        //Moving along file
        if (destSquare.getFile()==currPos.getFile()) {
            res = BoardUtilities.pieceInWayFile(currPos,destSquare,board);
        } else {
            return false;
        }

        if (res == null) {
            makeMove(currPos,destSquare);
            return true;
        } else {
            if (res == destSquare && res.occupyingPiece.getColor() != this.getColor()) {
                return true;
            } else {
                return false;
            }
        }
    }

}
