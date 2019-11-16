package tests;

import models.*;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for pawn class
 */
public class PawnTests {

    @Test
    public void ValidMove_ValidMoves_True() {
        Board b = new Board();
        Pawn pawn = new Pawn(Color.black, b.getSquareAt(2,6));
        Square curr = b.getSquareAt(2,6);

        //Test move forward to capture opponent's piece that's diagonally in front of it
        Square dest = b.getSquareAt(1, 5);
        b.placePieceAt(dest, new Queen(Color.white, dest));

        Boolean res = pawn.validMove(curr, dest, b);
        assertTrue(res);

        //Test move 1 forward
        b.placePieceAt(curr, pawn);
        dest = b.getSquareAt(2,5);
        res = pawn.validMove(curr, dest, b);
        assertTrue(res);

        //Test move 2 forward on first turn
        b.placePieceAt(curr, pawn);
        dest = b.getSquareAt(2, 4);
        res = pawn.validMove(curr,dest, b);
        assertTrue(res);
    }

    @Test
    public void ValidMove_InvalidMove_False() {
        Board b = new Board();
        Pawn pawn = new Pawn(Color.black, b.getSquareAt(2,6));

        //Test out of bound
        Square curr = new Square(9,9);
        Square dest = new Square(2,4);

        Boolean res = pawn.validMove(curr,dest,b);
        assertEquals(false, res);

        //Test move forward 1 blocked
        curr = b.getSquareAt(2,6);
        dest = b.getSquareAt(2,5);
        b.placePieceAt(curr.getFile(),curr.getRank(), pawn);
        b.placePieceAt(dest.getFile(), dest.getRank(), new Knight(Color.black, dest));

        res = pawn.validMove(curr,dest, b);
        assertEquals(false, res);

        //Test move adjacent to try to capture
        dest = b.getSquareAt(1,5);

        res = pawn.validMove(curr,dest, b);
        assertEquals(false, res);

        //Test move forward 2 blocked on first move
        dest = b.getSquareAt(2,4);

        res = pawn.validMove(curr,dest,b);
        assertEquals(false, res);
    }

}