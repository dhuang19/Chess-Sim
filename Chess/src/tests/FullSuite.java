package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BoardTests.class,
        UtilityTests.class,
        PieceTests.class,
        BishopTests.class,
        KingTests.class,
        KnightTests.class,
        PawnTests.class,
        ChessControllerTests.class,
        BarryTests.class,
        PrincessTests.class,
        QueenTests.class} )
/**
 * Runs all unit test suites
 */
public final class FullSuite {}
