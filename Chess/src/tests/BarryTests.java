package tests;

import models.Barry;
import models.Board;
import models.King;
import models.Square;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import utilities.DefaultChessValues;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Unit tests for Barry chess piece class.
 */
public class BarryTests {

    private Board b;
    private Barry _barry;
    private Square _barryInitSquare;

    @Test
    public void ValidMove_ValidMove_True() {
        b = new Board();
        _barryInitSquare = b.getSquareAt(4,2);
        _barry = new Barry(DefaultChessValues._colorPlayer1, _barryInitSquare);
        b.placePieceAt(_barryInitSquare, _barry);

        Boolean bool = _barry.validMove(_barryInitSquare, b.getSquareAt(4,4), b);
        assertTrue(bool);
    }

    @Test
    public void ValidMove_InvalidMove_False() {
        b = new Board();
        _barryInitSquare = b.getSquareAt(4,2);
        _barry = new Barry(DefaultChessValues._colorPlayer1, _barryInitSquare);
        b.placePieceAt(_barryInitSquare, _barry);

        b.placePieceAt(4,4, new King(Color.black, b.getSquareAt(4, 4)));
        Boolean bool = _barry.validMove(_barryInitSquare, b.getSquareAt(4,4), b);
        assertFalse(bool);

        b.placePieceAt(_barryInitSquare, _barry);
        bool = _barry.validMove(_barryInitSquare, new Square(9,1), b);
        assertFalse(bool);
    }
}
