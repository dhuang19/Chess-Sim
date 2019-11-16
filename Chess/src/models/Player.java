package models;

import utilities.DefaultChessValues;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class for player
 */
public class Player {
    /**
     * Board that listens for Player requests
     */
    private Board _board;

    /**
     * Color of player's team, either white or black
     */
    private Color _playerColor;

    /**
     * List of pieces this player has
     */
    private ArrayList<Piece> _pieces;

    /*
    Custom name
     */
    private String _name;

    public Player(Board boardListener, Color color, String name) {
        _board = boardListener;
        _playerColor = color;
        _name = name;

        //Initialize pieces belonging to player
        _pieces = new ArrayList<Piece>();
        initializeDefaultPieces();
    }

    /**
     * Request to move a piece on board
     * @param currPos
     * Current square piece to move is on
     * @param destSquare
     * Square to move piece to
     * @return
     * If move is valid
     */
    public boolean movePiece(Square currPos, Square destSquare) {
        return _board.playRequested(currPos, destSquare);
    }

    /**
     * Default initializer for chess pieces
     */
    private void initializeDefaultPieces() {
        Color fillColor = _playerColor;
        int rankBack;
        int rankFront;
        int customRank;

        if (_playerColor == DefaultChessValues._colorPlayer1) {
            rankBack = 0;
            rankFront = 1;
            customRank = 2;
        } else {
            rankBack = DefaultChessValues._defaultSize-1;
            rankFront = DefaultChessValues._defaultSize-2;
            customRank = DefaultChessValues._defaultSize-3;
        }

        //Initialize Pawns
        for (int i = 0; i < DefaultChessValues._defaultSize; i++) {
            initializePiece(i, rankFront, new Pawn(fillColor, _board.getSquareAt(i, rankFront)));
        }
        //Initialize Rooks
        initializePiece(0,rankBack, new Rook(fillColor, _board.getSquareAt(0,rankBack)));
        initializePiece(7,rankBack, new Rook(fillColor, _board.getSquareAt(7,rankBack)));
        //Initialize Knights
        initializePiece(1,rankBack, new Knight(fillColor, _board.getSquareAt(1, rankBack)));
        initializePiece(6, rankBack, new Knight(fillColor, _board.getSquareAt(6, rankBack)));
        //Initialize Bishops
        initializePiece(2,rankBack, new Bishop(fillColor, _board.getSquareAt(2, rankBack)));
        initializePiece(5, rankBack, new Bishop(fillColor, _board.getSquareAt(5, rankBack)));
        //Initialize Queen
        initializePiece(3,rankBack, new Queen(fillColor, _board.getSquareAt(3, rankBack)));
        //Initialize King
        initializePiece(4, rankBack, new King(fillColor, _board.getSquareAt(4,rankBack)));
        //Initialize Princess
        initializePiece(3, customRank, new Princess(fillColor, _board.getSquareAt(3, customRank)));
        //Initialize Barry
        initializePiece(4, customRank, new Barry(fillColor, _board.getSquareAt(4, customRank)));
    }

    /**
     * Helper for initializeDefaultPieces
     * @param fileIdx
     * File index
     * @param rankIdx
     * Rank index
     * @param piece
     * Chess piece to initialize
     */
    private void initializePiece(int fileIdx, int rankIdx, Piece piece) {
        _pieces.add(piece);
        _board.placePieceAt(fileIdx,rankIdx,piece);
    }

    public void setName(String name) {
        _name = name;
    }

}
