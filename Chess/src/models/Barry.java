package models;

import utilities.DefaultChessValues;

import java.awt.*;

/**
 * Barry chess piece (short for Barrier)
 * Barry starts in front of the pawn in front of the King
 * It can move 2 forward only. It acts like a barrier; it cannot capture or be captured.
 */
public class Barry extends Piece {

    public Barry(Color color, Square square) {
        super(color, square);
    }

    @Override
    public boolean validMove(Square currPos, Square destSquare, Board board) {
        if (!super.validMove(currPos, destSquare, board)) {
            return false;
        }

        if ((currPos.getFile() == destSquare.getFile())) {
            if ((this.getColor() == DefaultChessValues._colorPlayer1
                && currPos.getRank() + 2 == destSquare.getRank())
                || ((this.getColor() == DefaultChessValues._colorPlayer2)
                && currPos.getRank() - 2 == destSquare.getRank())) {
                if (destSquare.occupyingPiece == null) {
                    return true;
                }
            }
        }
        return false;
    }

}
