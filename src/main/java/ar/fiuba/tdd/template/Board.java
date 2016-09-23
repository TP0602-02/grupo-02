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
            board.add(inner);
        }
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

    ArrayList<CellContent<?>> getValues(int row, int column) {
        return getCell(row, column).getContents();
    }

    void setValue(int row, int column, CellContent content) {
        getCell(row, column).setContent(content);
    }
}
