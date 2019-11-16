package models;

import utilities.BoardUtilities;

import java.awt.*;

/**
 * Queen chess piece class
 */
public class Queen extends Piece {

    public Queen(Color color, Square square) {
        super(color, square);
    }

    //The queen combines the power of the rook and bishop
    // and can move any number of squares along rank, file, or diagonal,
    // but it may not leap over other pieces.
    @Override
    public boolean validMove(Square currPos, Square destSquare, Board board) {
        if (!super.validMove(currPos, destSquare, board)) {
            return false;
        }

        Square res = null;
        //Moving along rank
        if (destSquare.getRank()==currPos.getRank()) {
            res = BoardUtilities.pieceInWayRank(currPos,destSquare,board);
        }
        //Moving along file
        else if (destSquare.getFile()==currPos.getFile()) {
            res = BoardUtilities.pieceInWayFile(currPos, destSquare, board);
        }
        //Moving along BL -> TR diagonal
        else if (BoardUtilities.pathIsDiagonal(currPos,destSquare)
                && (BoardUtilities.calculateSlope(currPos,destSquare) > 0)) {
            res = BoardUtilities.pieceInWayDiagonal_BL_TR(currPos,destSquare,board);
        }
        //Moving along BR -> TL diagonal
        else if (BoardUtilities.pathIsDiagonal(currPos,destSquare)
                && (BoardUtilities.calculateSlope(currPos,destSquare) < 0)) {
            res = BoardUtilities.pieceInWayDiagonal_BR_TL(currPos,destSquare,board);
        }

        if (res == null) {
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
