package ar.fiuba.tdd.template;

class Board {

    private int[][] board;
    private int width;  // number of columns
    private int height; // number of rows

    Board(int height, int width) {
        board = new int[height][width];
        this.height = height;
        this.width = width;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                board[row][col] = 0;
            }
        }
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    int getValue(int row, int column) {
        return board[row][column];
    }
}
