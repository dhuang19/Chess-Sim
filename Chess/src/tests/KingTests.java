package tests;

import models.Board;
import models.King;
import models.Queen;
import models.Square;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class KingTests {

    @Test
    public void ValidMove_InvalidMove_False() {
        Board b = new Board();
        King king = new King(Color.white, b.getSquareAt(3,4));

        //Test out of bound
        Square curr = new Square(9,9);
        Square dest = new Square(2,4);

        Boolean res = king.validMove(curr,dest,b);
        assertEquals(false, res);

        //Test friendly piece blocking
        curr = b.getSquareAt(3,4);
        dest = b.getSquareAt(4,4);
        b.placePieceAt(curr.getFile(), curr.getRank(), king);
        b.placePieceAt(dest.getFile(), dest.getRank(), new Queen(Color.white, dest));

        res = king.validMove(curr,dest,b);
        assertEquals(false, res);
        //Clean up
        dest.occupyingPiece = null;
        b.placePieceAt(curr.getFile(), curr.getRank(), king);

        //Test move more than 1 square
        dest = b.getSquareAt(5,4);

        res = king.validMove(curr,dest,b);
        assertEquals(false,res);
    }

    @Test
    public void ValidMove_ValidMove_True() {
        Board b = new Board();
        King king = new King(Color.white, b.getSquareAt(3,4));

        //Test no blocking
        Square curr = b.getSquareAt(3,4);
        Square dest = b.getSquareAt(4,5);
        b.placePieceAt(curr.getFile(), curr.getRank(), king);

        Boolean res = king.validMove(curr,dest,b);
        assertEquals(true, res);
        //Clean up
        dest.occupyingPiece = null;
        b.placePieceAt(curr.getFile(), curr.getRank(), king);

        //Test capture
        b.placePieceAt(dest.getFile(), dest.getRank(), new Queen(Color.black, dest));

        res = king.validMove(curr,dest,b);
        assertEquals(true, res);
        //Clean up
        dest.occupyingPiece = null;
        b.placePieceAt(curr.getFile(), curr.getRank(), king);

        //Test move along file
        dest = b.getSquareAt(3,5);
        res = king.validMove(curr,dest,b);
        assertEquals(true, res);
        //Clean up
        dest.occupyingPiece = null;
        b.placePieceAt(curr.getFile(), curr.getRank(), king);

        //Test move along BR->TL diagonal
        dest = b.getSquareAt(2,5);
        res = king.validMove(curr,dest,b);
        assertEquals(true, res);
    }
}
