package com.kearny.puissance4;

import java.util.Scanner;

class Game {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    private char[][] grid = new char[ROWS][COLUMNS];

    void start() {
        initializeGrid();
        System.out.println("First player in yellow [Y]");
        System.out.println("Second player in Red [R]");
        showGrid();

        Scanner scanner = new Scanner(System.in);

        int yellowCol = 0;
        while (yellowCol < 1 || yellowCol > 7) {
            System.out.println("Yellow player in which column do you want to play ? Pick 1 to 7");

            try {
                yellowCol = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                yellowCol = 0;
            }
        }

        if (placeMoveOnGrid(yellowCol, 'Y')) {
            showGrid();
        }

        int redCol = 0;
        while (redCol < 1 || redCol > 7) {
            System.out.println("Red player in which column do you want to play ? Pick 1 to 7");

            try {
                redCol = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                redCol = 0;
            }
        }

        if (placeMoveOnGrid(redCol, 'R')) {
            showGrid();
        }

        scanner.close();
    }

    private boolean placeMoveOnGrid(int col, char r) {
        col = col - 1;
        boolean placed = false;

        for (int i = (ROWS - 1); i >= 0; i--) {
            char c = grid[i][col];

            if (c == '_') {
                grid[i][col] = r;
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

    private void showGrid() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (col == COLUMNS - 1)
                    System.out.print("|" + grid[row][col] + "|");
                else
                    System.out.print("|" + grid[row][col]);
            }

            System.out.println();
        }
    }

    public char[][] getGrid() {
        return this.grid;
    }
}
