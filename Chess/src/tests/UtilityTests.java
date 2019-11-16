package tests;
import controllers.ChessController;
import models.*;
import utilities.BoardUtilities;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit tests for Utilities
 */
public class UtilityTests {

    @Test
    public void PieceInWayRank_ValidArgs_Null() {
        Board testBoard = new Board();
        testBoard.placePieceAt(4,3,new King(Color.white, testBoard.getSquareAt(3,3)));
        Square dest = testBoard.getSquareAt(3,3);

        Square res = BoardUtilities.pieceInWayRank(testBoard.getSquareAt(4,3), dest, testBoard);
        assertEquals(null, res);

        testBoard.placePieceAt(3,4, new Queen(Color.white, testBoard.getSquareAt(3,4)));
        dest = testBoard.getSquareAt(4,4);
        res = BoardUtilities.pieceInWayRank(testBoard.getSquareAt(3,4), dest, testBoard);
        assertEquals(null, res);
    }

    @Test
    public void PieceInWayRank_ValidArgs_Piece() {
        Board testBoard = new Board();
        testBoard.placePieceAt(3,3,new King(Color.white, testBoard.getSquareAt(3,3)));
        testBoard.placePieceAt(1,3,new Queen(Color.white, testBoard.getSquareAt(1,3)));
        Square dest = testBoard.getSquareAt(0,3);

        Square res = BoardUtilities.pieceInWayRank(testBoard.getSquareAt(3,3), dest, testBoard);
        assertEquals(testBoard.getSquareAt(1,3).occupyingPiece, res.occupyingPiece);
    }

    @Test
    public void PieceInWayFile_ValidArgs_Null() {
        Board testBoard = new Board();
        testBoard.placePieceAt(3,3,new King(Color.white, testBoard.getSquareAt(3,3)));
        Square dest = testBoard.getSquareAt(3,6);

        Square res = BoardUtilities.pieceInWayFile(testBoard.getSquareAt(3,3), dest, testBoard);
        assertEquals(null, res);
    }

    @Test
    public void PieceInWayFile_ValidArgs_Piece() {
        Board testBoard = new Board();
        testBoard.placePieceAt(3,3,new King(Color.white, testBoard.getSquareAt(3,3)));
        testBoard.placePieceAt(3,0,new Queen(Color.black, testBoard.getSquareAt(3,0)));
        Square dest = testBoard.getSquareAt(3,0);

        Square res = BoardUtilities.pieceInWayFile(testBoard.getSquareAt(3,3), dest, testBoard);
        assertEquals(testBoard.getSquareAt(3,0).occupyingPiece, res.occupyingPiece);
    }

    @Test
    public void PieceInWayDiagonalBLTR_ValidArgs_Null() {
        Board testBoard = new Board();
        testBoard.placePieceAt(2,3,new King(Color.white, testBoard.getSquareAt(2,3)));
        Square dest = testBoard.getSquareAt(5,6);

        Square res = BoardUtilities.pieceInWayDiagonal_BL_TR(testBoard.getSquareAt(2,3), dest, testBoard);
        assertEquals(null, res);
    }

    @Test
    public void PieceInWayDiagonalBLTR_ValidArgs_Piece() {
        Board testBoard = new Board();
        testBoard.placePieceAt(5,6,new King(Color.white, testBoard.getSquareAt(5,6)));
        testBoard.placePieceAt(2,3,new Queen(Color.black, testBoard.getSquareAt(2,3)));
        Square dest = testBoard.getSquareAt(1,2);

        Square res = BoardUtilities.pieceInWayDiagonal_BL_TR(testBoard.getSquareAt(5,6), dest, testBoard);
        assertEquals(testBoard.getSquareAt(2,3).occupyingPiece, res.occupyingPiece);
    }

    @Test
    public void PieceInWayDiagonalBRTL_ValidArgs_Null() {
        Board testBoard = new Board();
        testBoard.placePieceAt(2,5,new King(Color.white, testBoard.getSquareAt(2,5)));
        testBoard.placePieceAt(4,4,new Queen(Color.black, testBoard.getSquareAt(4,5)));
        Square dest = testBoard.getSquareAt(5,2);

        Square res = BoardUtilities.pieceInWayDiagonal_BR_TL(testBoard.getSquareAt(2,5), dest, testBoard);
        assertEquals(null, res);
    }

    @Test
    public void PieceInWayDiagonalBRTL_ValidArgs_Piece() {
        Board testBoard = new Board();
        testBoard.placePieceAt(5,2,new King(Color.white, testBoard.getSquareAt(5,2)));
        testBoard.placePieceAt(4,3,new Knight(Color.white, testBoard.getSquareAt(4,3)));
        Square dest = testBoard.getSquareAt(2,5);

        Square res = BoardUtilities.pieceInWayDiagonal_BR_TL(testBoard.getSquareAt(5,2), dest, testBoard);
        assertEquals(testBoard.getSquareAt(4,3).occupyingPiece, res.occupyingPiece);
    }

    @Test
    public void CalculateSlope_ValidArgs_CorrectSlope() {
        Square curr = new Square(0,0);
        Square dest = new Square(2,2);

        assertEquals(1, BoardUtilities.calculateSlope(curr,dest),0.0001);
    }

    @Test
    public void PathIsDiagonal_ValidArgs_CorrectBool() {
        Square curr = new Square(0,0);
        Square dest = new Square(2,2);
        assertEquals(true, BoardUtilities.pathIsDiagonal(curr,dest));

        curr = new Square(1,2);
        dest = new Square(1,6);
        assertEquals(false, BoardUtilities.pathIsDiagonal(curr,dest));

    }

    @Test
    public void GetAllActivePieces_DefaultBoard_CorrectPieces() {
        ChessController cc = new ChessController(8,8);
        ArrayList<Square> whiteList = BoardUtilities.getAllActivePieces(Color.white, cc.board);

        assertTrue(whiteList != null);
        assertEquals(16, whiteList.size());
    }
}
