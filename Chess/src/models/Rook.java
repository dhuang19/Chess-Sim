package models;

import utilities.BoardUtilities;

import java.awt.*;

/**
 * Class for rook chess piece
 */
public class Rook extends Piece{

    public Rook(Color color, Square square) {
        super(color, square);
    }

//    The rook can move any number of squares along any rank or file,
//    but may not leap over other pieces.
    @Override
    public boolean validMove(Square currPos, Square destSquare, Board board) {
        if (!super.validMove(currPos,destSquare,board)) {
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
        } else {
            return false;
        }

        if (res != null) {
            if (res == destSquare) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

}
