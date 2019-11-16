package tests;

import models.*;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class PrincessTests {

    @Test
    public void ValidMove_ValidMove_True() {
        Board b = new Board();
        Square curr = b.getSquareAt(3,5);
        Square dest = b.getSquareAt(3,2);
        Princess princess = new Princess(Color.black, curr);

        //Test move no capture
        Boolean res = princess.validMove(curr, dest, b);
        assertTrue(res);

        //Test move with capture
        dest = b.getSquareAt(3,4);
        b.placePieceAt(dest, new Queen(Color.white, dest));

        res = princess.validMove(curr, dest, b);
        assertTrue(res);
    }

    @Test
    public void ValidMove_InvalidMove_False() {
        Board b = new Board();
        Square curr = b.getSquareAt(3,5);
        Square dest = new Square(9,3);
        Princess princess = new Princess(Color.black, curr);

        //Test out of bounds
        Boolean res = princess.validMove(curr,dest,b);
        assertFalse(res);

        //Test not moving along file
        dest = b.getSquareAt(5,5);
        res = princess.validMove(curr, dest, b);
        assertFalse(res);

        //Test no jumping over pieces
        dest = b.getSquareAt(3, 2);
        b.placePieceAt(dest, new Knight(Color.black, dest));

        res = princess.validMove(curr, dest, b);
        assertFalse(res);
    }

}
