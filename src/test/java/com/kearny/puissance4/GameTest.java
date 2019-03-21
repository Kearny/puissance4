package com.kearny.puissance4;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GameTest {
  @Test
  public void shouldReturnPlayer1AsWinner() {
    var game = new Game();

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
    assertNull(game.placeMoveOnGrid(PlayerEnum.PLAYER1, 2).winner);
    assertNull(game.placeMoveOnGrid(PlayerEnum.PLAYER1, 2).winner);
    assertNull(game.placeMoveOnGrid(PlayerEnum.PLAYER1, 2).winner);
  }
}
