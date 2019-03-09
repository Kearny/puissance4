package com.kearny.puissance4;

class Game {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    private char[][] grid = new char[ROWS][COLUMNS];

    public boolean placeMoveOnGrid(int player, int column) {
        column = column - 1;
        boolean placed = false;

        for (int i = (ROWS - 1); i >= 0; i--) {
            char c = grid[i][column];

            if (c == '_') {
                if (1 == player)
                    grid[i][column] = 'y';
                else
                    grid[i][column] = 'r';
                placed = true;

                break;
            }
        }

        return placed;
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
