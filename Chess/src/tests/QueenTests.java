package tests;

import models.Board;
import models.Knight;
import models.Queen;
import models.Square;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Unit tests for Queen chess piece
 */
public class QueenTests {

    @Test
    public void ValidMove_ValidMove_True() {
        Board b = new Board();
        Square curr = b.getSquareAt(3,4);
        Square dest = b.getSquareAt(0,4);
        Queen queen = new Queen(Color.white, curr);
        b.placePieceAt(curr, queen);

        //Test move along rank
        Boolean res = queen.validMove(curr, dest, b);
        assertTrue(res);

        //Test move along file
        dest = b.getSquareAt(3,1);
        res = queen.validMove(curr,dest,b);
        assertTrue(res);

        //Test move along diagonal
        dest = b.getSquareAt(4,3);
        res = queen.validMove(curr,dest,b);
        assertTrue(res);


        //Test capture
        dest = b.getSquareAt(6,7);
        b.placePieceAt(dest, new Queen(Color.black, dest));
        res = queen.validMove(curr,dest,b);
        assertTrue(res);
    }

    @Test
    public void ValidMove_InvalidMove_False() {
        Board b = new Board();
        Square curr = b.getSquareAt(3,4);
        Square dest = new Square(10, 11);
        Queen queen = new Queen(Color.white, curr);
        b.placePieceAt(curr, queen);

        //Test out of bounds
        Boolean res = queen.validMove(curr, dest,b);
        assertFalse(res);

        //Test no leaping over other pieces
        dest = b.getSquareAt(6,7);
        b.placePieceAt(dest, new Knight(Color.white, dest));
        res = queen.validMove(curr,dest, b);
        assertFalse(res);

    }

}
