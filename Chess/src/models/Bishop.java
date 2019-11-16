package models;

import utilities.BoardUtilities;

import java.awt.*;

/**
 * Bishop chess piece class
 */
public class Bishop extends Piece {

    public Bishop(Color color, Square square) {
        super(color, square);
    }

    //The bishop can move any number of squares diagonally, but may not leap over other pieces.
    @Override
    public boolean validMove(Square currPos, Square destSquare, Board board) {
        if (!super.validMove(currPos, destSquare, board)) {
            return false;
        }

        float slope = BoardUtilities.calculateSlope(currPos,destSquare);
        Square res = null;
        if (slope > 0) {
            //Moving along BL -> TR path
            res = BoardUtilities.pieceInWayDiagonal_BL_TR(currPos, destSquare, board);
        } else {
            //Moving along BR -> TL path
            res = BoardUtilities.pieceInWayDiagonal_BR_TL(currPos, destSquare, board);
        }

        if (res != null) {
            if (res == destSquare && (res.occupyingPiece.getColor() != this.getColor())) {
                return true;
            } else {
                //Invalid move
                return false;
            }
        } else {
            return true;
        }
    }

}
