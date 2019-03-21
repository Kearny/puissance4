package com.kearny.puissance4;

class Game {
  static final int ROWS = 6;
  static final int COLUMNS = 7;

  private int[][] grid = new int[ROWS][COLUMNS];

  MoveResult placeMoveOnGrid(PlayerEnum player, int column) {
    MoveResult moveResult = new MoveResult();

    // For the player first column is 1
    column = column - 1;

    for (int i = (ROWS - 1); i >= 0; i--) {
      int value = grid[i][column];

      if (value == 0) {
        grid[i][column] = player.getValue();

        break;
      }
    }

    moveResult.grid = this.grid;

    if (checkWinConditions(player.getValue(), column)) {
      moveResult.winner = player;
    }

    return moveResult;
  }

  private boolean checkWinConditions(int player, int column) {
    return checkVertical(player, column);
  }

  private boolean checkVertical(int player, int col) {
    int count = 0;
    for (int i = 0; i < ROWS; i++) {
      if (grid[i][col] == player)
        count++;
      else
        count = 0;

      if (count == 4) {
        return true;
      }
    }

    return false;
  }

  void initializeGrid() {
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLUMNS; col++) {
        grid[row][col] = 0;
      }
    }
  }

  int[][] getGrid() {
    return this.grid;
  }

}
