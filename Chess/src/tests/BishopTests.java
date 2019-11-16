package tests;

import models.Bishop;
import models.Board;
import models.Queen;
import models.Square;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for Bishop class
 */
public class BishopTests {

    @Test
    public void ValidMove_InvalidMove_False() {
        Board b = new Board();
        Bishop bishop = new Bishop(Color.white, b.getSquareAt(4,3));

        //Test out of bound
        Square curr = new Square(9,9);
        Square dest = new Square(2,4);

        Boolean res = bishop.validMove(curr,dest,b);
        assertEquals(false, res);

        //Test leap over other pieces
        curr = b.getSquareAt(4,3);
        b.placePieceAt(5,4,new Queen(Color.white, b.getSquareAt(5,4)));
        dest = b.getSquareAt(6,5);

        res = bishop.validMove(curr,dest,b);
        assertEquals(false, res);
    }

    @Test
    public void ValidMove_ValidMove_True() {
        Board b = new Board();

        //Test no blocking
        Square curr = b.getSquareAt(4,3);
        Bishop bishop = new Bishop(Color.white, curr);
        Square dest = b.getSquareAt(6,1);

        Boolean res = bishop.validMove(curr,dest,b);
        assertEquals(true,res);
        //Clean up
        dest.occupyingPiece = null;

        //Test capture
        b.placePieceAt(curr.getFile(), curr.getRank(), bishop);
        b.placePieceAt(dest.getFile(), dest.getRank(), new Bishop(Color.black, dest));

        res = bishop.validMove(curr,dest,b);
        assertEquals(true, res);
    }

}
