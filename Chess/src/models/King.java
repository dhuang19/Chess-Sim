package models;

import utilities.BoardUtilities;

import java.awt.*;

/**
 * King chess piece class
 */
public class King extends Piece {

    public King(Color color, Square square) {
        super(color, square);
    }

    //The king moves one square in any direction.
    @Override
    public boolean validMove(Square currPos, Square destSquare, Board board) {
        //Check destination is within bounds
        if (!super.validMove(currPos,destSquare,board)) {
            return  false;
        }

        Square res = null;
        //Moving along rank
        if (Math.abs(destSquare.getFile() - currPos.getFile()) == 1
            && destSquare.getRank() == currPos.getRank()) {
            res = BoardUtilities.pieceInWayRank(currPos,destSquare,board);
        }
        //Moving along file
        else if (Math.abs(destSquare.getRank() - currPos.getRank()) == 1
                && destSquare.getFile() == currPos.getFile()) {
            res = BoardUtilities.pieceInWayFile(currPos, destSquare, board);
        }
        //Moving along BL -> TR diagonal
        else if ((Math.abs(currPos.getFile() - destSquare.getFile()) == 1)
                && (Math.abs(currPos.getRank() - destSquare.getRank()) == 1)
                && (BoardUtilities.calculateSlope(currPos,destSquare) > 0)) {
            res = BoardUtilities.pieceInWayDiagonal_BL_TR(currPos,destSquare,board);
        }
        //Moving along BR -> TL diagonal
        else if ((Math.abs(currPos.getFile() - destSquare.getFile()) == 1)
                && (Math.abs(currPos.getRank() - destSquare.getRank()) == 1)
                && (BoardUtilities.calculateSlope(currPos,destSquare) < 0)) {
            res = BoardUtilities.pieceInWayDiagonal_BR_TL(currPos,destSquare,board);
        } else {
            return false;
        }

        if (res != null) {
            if (res.occupyingPiece.getColor() != this.getColor()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

}
