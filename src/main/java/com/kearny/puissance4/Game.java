package com.kearny.puissance4;

class Game {
    private static final int ROWS = 6;

    private static final int COLUMNS = 7;

    void start() {
        showGrid();
    }

    private void showGrid() {
        for (int row = 0; row <= ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (col == COLUMNS - 1)
                    System.out.print("|" + col + "|");
                else
                    System.out.print("|" + col);
            }

            System.out.println();
        }
    }
}
