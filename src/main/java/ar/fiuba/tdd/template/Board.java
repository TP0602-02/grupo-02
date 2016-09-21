package ar.fiuba.tdd.template;

class Board<T> {

    private Cell<T>[][] board;
    private int width;  // number of columns
    private int height; // number of rows

    Board(int height, int width) {
        board = new Cell[height][width];
        this.height = height;
        this.width = width;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                board[row][col] = new Cell<>();
            }
        }
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    private Cell<T> getCell(int row, int column) {
        return board[row][column];
    }

    T getValue(int row, int column) {
        return getCell(row, column).getValue();
    }

    void setValue(int row, int column, T value) {
        getCell(row, column).setValue(value);
    }
}
