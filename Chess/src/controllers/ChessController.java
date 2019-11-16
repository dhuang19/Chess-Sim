package controllers;

import models.Board;
import models.Player;
import utilities.DefaultChessValues;
import utilities.ViewUtilities;
import views.ChessView;

import javax.swing.*;

/**
 * Controller for chess game
 */
public class ChessController {

    /**
     * Player 1
     */
    public Player playerA;

    /**
     * Player 2
     */
    public Player playerB;

    /**
     * Chess board being played on
     */
    public Board board;

    public ChessController(int ranks, int files) {
        board = new Board(ranks, files);
        playerA = new Player(board, DefaultChessValues._colorPlayer1, "defaultName");
        playerB = new Player(board, DefaultChessValues._colorPlayer2, "defaultName");
    }

    public ChessController(int ranks, int files, String player1, String player2) {
        board = new Board(ranks, files);
        playerA = new Player(board, DefaultChessValues._colorPlayer1, player1);
        playerB = new Player(board, DefaultChessValues._colorPlayer2, player2);
    }

    /**
     * Main function executing game loop
     * @param args
     */
    public static void main(String[] args) {
        ChessController cc = new ChessController(8,8);
        ChessView cv = new ChessView(cc.board);

        //Retrieve player names
        cc.playerA.setName(ViewUtilities.promptForName(1));
        cc.playerB.setName(ViewUtilities.promptForName(2));


    }

}
