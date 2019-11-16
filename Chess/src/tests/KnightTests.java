package tests;

import models.*;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for Knight class
 */
public class KnightTests {

    @Test
    public void ValidMove_InvalidMove_False() {
        Board b = new Board();
        Knight knight = new Knight(Color.white, b.getSquareAt(3,4));

        //Test out of bound
        Square curr = new Square(9,9);
        Square dest = new Square(2,4);

        Boolean res = knight.validMove(curr,dest,b);
        assertEquals(false, res);

        //Test invalid movement pattern
        curr = b.getSquareAt(3,4);
        dest = b.getSquareAt(5,6);
        b.placePieceAt(curr.getFile(), curr.getRank(), knight);

        res = knight.validMove(curr,dest, b);
        assertEquals(false, res);

        //Test friendly piece on destination
        dest = b.getSquareAt(5,5);
        b.placePieceAt(dest.getFile(), dest.getRank(), new Bishop(Color.white, dest));

        res = knight.validMove(curr,dest,b);
        assertEquals(false, res);
    }

    @Test
    public void ValidMove_ValidMove_True() {
        Board b = new Board();

        //Test capture
        Square curr = b.getSquareAt(3,4);
        Knight knight = new Knight(Color.white, curr);

        Square dest = b.getSquareAt(1,3);
        b.placePieceAt(curr.getFile(), curr.getRank(), knight);
        b.placePieceAt(dest.getFile(), dest.getRank(), new King(Color.black, dest));

        Boolean res = knight.validMove(curr,dest,b);
        assertEquals(true, res);
        //Clean up
        dest.occupyingPiece = null;
        b.placePieceAt(curr.getFile(), curr.getRank(), knight);

        //Test no blocking
        res = knight.validMove(curr,dest,b);
        assertEquals(true, res);
    }
}
