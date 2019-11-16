package models;

import utilities.BoardUtilities;
import utilities.DefaultChessValues;

import java.awt.*;

/**
 * Pawn chess piece class
 */
public class Pawn extends Piece {

    public Pawn(Color color, Square square) {
        super(color, square);
    }

//    The pawn may move forward to the unoccupied square immediately in front
//    of it on the same file; or on its first move it may advance two squares along
//    the same file provided both squares are unoccupied;
//    or it may move to a square occupied by an opponent's piece which is diagonally
//    in front of it on an adjacent file, capturing that piece.
    @Override
    public boolean validMove(Square currPos, Square destSquare, Board board) {
        if (!super.validMove(currPos, destSquare, board)) {
            return false;
        }

        //Depending on team, forward is +1/-1 in rank
        int forward = 0;
        int initialRank = 0;
        if (this.getColor() == DefaultChessValues._colorPlayer1) {
            forward = 1;
            initialRank = 1;
        } else {
            forward = -1;
            initialRank = DefaultChessValues._defaultSize-2;
        }

        // Move forward adjacent
        if ((destSquare.getRank() == currPos.getRank() + forward)
                && Math.abs(destSquare.getFile() - currPos.getFile()) == 1) {
            if (destSquare.occupyingPiece != null) {
                if (destSquare.occupyingPiece.getColor() != this.getColor()) {
                    return true;
                }
            } else {
                return false;
            }
        }
        //Move 1 forward
        else if (destSquare.getRank() == currPos.getRank() + forward) {
            if (destSquare.occupyingPiece != null) {
                return false;
            } else {
                return true;
            }
        }
        // Move 2 on first turn
        else if ((currPos.getRank() == initialRank)
                && (destSquare.getRank() == currPos.getRank() + (2*forward))) {
            Square res = BoardUtilities.pieceInWayFile(currPos,destSquare,board);
            if (res != null) {
                return false;
            } else {
                return true;
            }
        }

        return false;
    }

}
