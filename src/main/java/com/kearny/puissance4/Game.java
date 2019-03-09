package com.kearny.puissance4;

class Game {
    static final int ROWS = 6;
    static final int COLUMNS = 7;

    private char[][] grid = new char[ROWS][COLUMNS];

    public MoveResult placeMoveOnGrid(PlayerEnum player, int column) {
        MoveResult moveResult = new MoveResult();

        // For the player first column is 1
        column = column - 1;

        for (int i = (ROWS - 1); i >= 0; i--) {
            char c = grid[i][column];

            if (c == '_') {
                if (PlayerEnum.PLAYER1 == player)
                    grid[i][column] = 'y';
                else
                    grid[i][column] = 'r';

                break;
            }
        }

        moveResult.grid = this.grid;
        moveResult.winner = checkWinner();

        return moveResult;
    }

    private PlayerEnum checkWinner() {
        return null;
    }

    public void initializeGrid() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                grid[row][col] = '_';
            }
        }
    }

    public char[][] getGrid() {
        return this.grid;
    }

}
