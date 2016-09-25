package ar.fiuba.tdd.template.model;

import java.util.ArrayList;

public class Board<T> {

    private ArrayList<ArrayList<Cell<T>>> board;
    private int width;  // number of columns
    private int height; // number of rows

    public Board(int height, int width) {
        board = new ArrayList<>();
        this.height = height;
        this.width = width;

        for (int col = 0; col < width; col++) {
            ArrayList<Cell<T>> inner = new ArrayList<>();
            for (int row = 0; row < height; row++) {
                inner.add(new Cell<>(row,col));
            }
            board.add(inner);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell<T> getCell(int row, int column) {
        return board.get(column).get(row);
    }

    public ArrayList<CellContent<?>> getContents(int row, int column) {
        return getCell(row, column).getContents();
    }

    public void setValue(int row, int column, CellContent content) {
        getCell(row, column).setContent(content);
    }

    public ArrayList<Cell<T>> getRow(Cell<T> cell) {

        // This methods returns the entire row of cells

        ArrayList<Cell<T>> cellsInRow = new ArrayList<>();
        int row = cell.getRow();
        for (int column = 0; column < width; column++) {
            cellsInRow.add(getCell(row,column));
        }

        return cellsInRow;
    }

    public ArrayList<Cell<T>> getColumn(Cell<T> cell) {

        // This methods returns the entire column of cells

        ArrayList<Cell<T>> cellsInColumn = new ArrayList<>();

        int column = cell.getRow();
        for (int row = 0; row < width; row++) {
            cellsInColumn.add(getCell(row,column));
        }

        return cellsInColumn;
    }

    public ArrayList<ArrayList<Cell<T>>> getRegion(Cell<T> cell) {

        // This method returns the region surrounding
        // the cell as well as the cell itself

        int row = cell.getRow();
        int column = cell.getColumn();

        // Temporary region created to test out this method

        ArrayList<ArrayList<Cell<T>>> region = new ArrayList<>();
        for (int regionCol = column - 1; regionCol < width; regionCol++) {
            ArrayList<Cell<T>> inner = new ArrayList<>();
            for (int regionRow = row - 1; regionRow < height; regionRow++) {
                inner.add(new Cell<>(regionRow,regionCol));
            }
            region.add(inner);
        }

        return region;
    }

}
