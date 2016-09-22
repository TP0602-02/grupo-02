package ar.fiuba.tdd.template;

import java.util.ArrayList;

class Board<T> {

    private ArrayList<ArrayList<Cell<T>>> board;
    private int width;  // number of columns
    private int height; // number of rows

    Board(int height, int width) {
        board = new ArrayList<>();
        this.height = height;
        this.width = width;

        for (int col = 0; col < width; col++) {
            ArrayList<Cell<T>> inner = new ArrayList<>();
            for (int row = 0; row < height; row++) {
                inner.add(new Cell<>());
            }
            //System.out.print(inner.size());
            board.add(inner);
        }
        //System.out.print(board.size());
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    private Cell<T> getCell(int row, int column) {
        return board.get(column).get(row);
    }

    T getValue(int row, int column) {
        return getCell(row, column).getValue();
    }

    void setValue(int row, int column, T value) {
        getCell(row, column).setValue(value);
    }
}
