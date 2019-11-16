package tests;

import com.sun.org.apache.xpath.internal.operations.Bool;
import models.*;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for Piece class
 */
public class PieceTests {

    @Test
    public void ValidMove_InvalidMove_False() {
        Board testBoard = new Board();
        Square curr = new Square(9,1);
        Square dest = new Square(1,1);
        King testPiece = new King(Color.white, curr);

        Boolean res = testPiece.validMove(curr,dest,testBoard);
        assertEquals(false, res);
    }

//    @Test
//    public void MakeMove_ValidArgs_UpdatesCorrectly() {
//        Board b = new Board();
//        Square curr = b.getSquareAt(2,6);
//        Square dest = b.getSquareAt(2, 5);
//        Pawn pawn = new Pawn(Color.black, curr);
//
//        b.placePieceAt(curr, pawn);
//        //No Capture
//        Boolean res = (Piece) pawn.makeMove(curr,dest);
//
//        assertTrue(res);
//        assertNull(curr.occupyingPiece);
//        assertEquals(dest.occupyingPiece, pawn);
//        assertEquals(pawn.currentPos, dest);
//
//        //Capture
//    }
}
