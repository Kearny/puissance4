package com.kearny.puissance4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameTest {
    @Test
    public void shouldReturnPlayer1AsWinner() {
        Game game = new Game();

        // VERTICAL CONDITION

        // Player 1 has 3 coins in a row col 2
        game.placeMoveOnGrid(PlayerEnum.PLAYER1, 2);
        game.placeMoveOnGrid(PlayerEnum.PLAYER1, 2);
        game.placeMoveOnGrid(PlayerEnum.PLAYER1, 2);

        assertEquals(PlayerEnum.PLAYER1, game.placeMoveOnGrid(PlayerEnum.PLAYER1, 2).winner);
    }

    @Test
    public void shouldReturnPlayer2AsWinner() {
        Game game = new Game();

        // VERTICAL CONDITION

        // Player 1 has 1 coin in col 5
        game.placeMoveOnGrid(PlayerEnum.PLAYER1, 5);

        // Player 2 has 3 coins in a row col 5
        game.placeMoveOnGrid(PlayerEnum.PLAYER2, 5);
        game.placeMoveOnGrid(PlayerEnum.PLAYER2, 5);
        game.placeMoveOnGrid(PlayerEnum.PLAYER2, 5);

        assertEquals(PlayerEnum.PLAYER2, game.placeMoveOnGrid(PlayerEnum.PLAYER2, 5).winner);
    }

    @Test
    public void shouldReturnNoWinner() {
        Game game = new Game();

        // VERTICAL CONDITION

        // Player 1 has 3 coins in a row col 2
        assertEquals(null, game.placeMoveOnGrid(PlayerEnum.PLAYER1, 2).winner);
        assertEquals(null, game.placeMoveOnGrid(PlayerEnum.PLAYER1, 2).winner);
        assertEquals(null, game.placeMoveOnGrid(PlayerEnum.PLAYER1, 2).winner);
    }
}