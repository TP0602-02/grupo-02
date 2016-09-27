package ar.fiuba.tdd.template.board;


import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.board.cell.CellContent;

import java.util.ArrayList;

public class Board {

    private ArrayList<ArrayList<Cell>> board;
    private int width;  // number of columns
    private int height; // number of rows

    public Board(int height, int width) {
        board = new ArrayList<>();
        this.height = height;
        this.width = width;

        for (int col = 0; col < width; col++) {
            ArrayList<Cell> inner = new ArrayList<>();
            for (int row = 0; row < height; row++) {
                inner.add(new Cell(row,col));
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

    public Cell getCell(int row, int column) {
        return board.get(column).get(row);
    }

    public ArrayList<CellContent> getContents(int row, int column) {
        return getCell(row, column).getContents();
    }

    public void setValue(int row, int column, CellContent content) {
        getCell(row, column).setContent(content);
    }

    public ArrayList<Cell> getRow(Cell cell) {

        // This methods returns the entire row of cells

        ArrayList<Cell> cellsInRow = new ArrayList<>();
        int row = cell.getRow();
        for (int column = 0; column < width; column++) {
            cellsInRow.add(getCell(row,column));
        }

        return cellsInRow;
    }

    public ArrayList<Cell> getColumn(Cell cell) {

        // This methods returns the entire column of cells

        ArrayList<Cell> cellsInColumn = new ArrayList<>();

        int column = cell.getRow();
        for (int row = 0; row < width; row++) {
            cellsInColumn.add(getCell(row,column));
        }

        return cellsInColumn;
    }

    public ArrayList<ArrayList<Cell>> getRegion(Cell cell) {

        // This method returns the region surrounding
        // the cell as well as the cell itself

        int row = cell.getRow();
        int column = cell.getColumn();

        // Temporary region created to test out this method

        ArrayList<ArrayList<Cell>> region = new ArrayList<>();
        for (int regionCol = column - 1; regionCol < width; regionCol++) {
            ArrayList<Cell> inner = new ArrayList<>();
            for (int regionRow = row - 1; regionRow < height; regionRow++) {
                inner.add(new Cell(regionRow,regionCol));
            }
            region.add(inner);
        }

        return region;
    }

}
