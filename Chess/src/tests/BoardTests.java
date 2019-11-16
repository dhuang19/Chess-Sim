package tests;

import models.Board;
import models.Pawn;
import models.Rook;
import utilities.DefaultChessValues;
import org.junit.Test;
import models.Square;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit tests for Board class
 */
public class BoardTests {
    @Test
    public void BoardConstructor_ValidDimensions_Initialized() {
        //Check dimensions
        Board testBoard = new Board(3,4);
        assertEquals(3,testBoard.getRankCount());
        assertEquals(4,testBoard.getFileCount());
    }

    @Test
    public void DefaultBoardConstructor_NoArgs_Initialized() {
        Board testBoard = new Board();
        assertEquals(DefaultChessValues._defaultSize, testBoard.getRankCount());
        assertEquals(DefaultChessValues._defaultSize, testBoard.getFileCount());
    }

    @Test
    public void GetSquare_ValidIdx_ReturnsCorrectSquare() {
        Board testBoard = new Board(2,2);
        assertEquals(Square.class, testBoard.getSquareAt(0,0).getClass());
    }

    public void GetSquare_InvalidIdx_Null() {
        Board testBoard = new Board(2,2);
        Square res = testBoard.getSquareAt(4,1);
        assertNull(res);
    }

    @Test
    public void PlacePiece_ValidInput_FillsSquare() {
        Board testBoard = new Board(2,2);
        Rook testPiece = new Rook(Color.white, testBoard.getSquareAt(1,1));
        testBoard.placePieceAt(1,1, testPiece);

        assertEquals(Rook.class, testBoard.getSquareAt(1,1).occupyingPiece.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void BoardConstructor_InvalidDimensions_ExceptionThrown() {
        Board testBoard = new Board(-1,-2);
    }

    @Test(expected =  IndexOutOfBoundsException.class)
    public void PlayRequested_InvalidDest_ExceptionThrown() {
        Board testBoard = new Board();
        Square curr = new Square(0,0);
        Square dest = new Square(9,9);

        Boolean res = testBoard.playRequested(curr,dest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void PlayRequested_InvalidCurr_ExceptionThrown() {
        Board testBoard = new Board();
        Square curr = testBoard.getSquareAt(0,0);
        Square dest = testBoard.getSquareAt(0,1);

        Boolean res = testBoard.playRequested(curr,dest);
    }

    @Test
    public void PlayRequested_InvalidMove_False() {
        Board testBoard = new Board();
        testBoard.placePieceAt(1,1,new Pawn(Color.white, testBoard.getSquareAt(1,1)));
        Square dest = testBoard.getSquareAt(1,4);

        Boolean res = testBoard.playRequested(testBoard.getSquareAt(1,1), dest);
        assertEquals(false,res);
    }
}