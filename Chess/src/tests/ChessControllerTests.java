package tests;

import controllers.ChessController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChessControllerTests {
    @Test
    public void Constructor_ValidInput_CreatesObject() {
        ChessController cc = new ChessController(8,8);
        assertEquals(cc.board.getRankCount(), 8);
        assertEquals(cc.board.getFileCount(), 8);
    }
}
