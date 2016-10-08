package ar.fiuba.tdd.template.board;


import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;

import java.util.ArrayList;

public class Board<T> {

    private ArrayList<ArrayList<Cell>> board;
    private int width;  // number of columns
    private int height; // number of rows
    private String cellType;

    public Board(int height, int width,String cellType) {
        board = new ArrayList<>();
        this.height = height;
        this.width = width;
        this.cellType = cellType;
        CellFactory cellFactory = new CellFactory();
        for (int col = 0; col < width; col++) {
            ArrayList<Cell> inner = new ArrayList<>();
            for (int row = 0; row < height; row++) {
                inner.add(cellFactory.createCell(this.cellType,row, col));
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

    public void setValues(int row, int column, ArrayList<CellContent> contents) {
        for (CellContent cellContent : contents) {
            getCell(row, column).setContent(cellContent);
        }
    }

    public ArrayList<Cell> getRow(Cell cell) {

        // This methods returns the entire row of cells

        ArrayList<Cell> cellsInRow = new ArrayList<>();
        int row = cell.getRow();
        for (int column = 0; column < width; column++) {
            cellsInRow.add(getCell(row, column));
        }

        return cellsInRow;
    }

    public ArrayList<Cell> getColumn(Cell cell) {

        // This methods returns the entire column of cells

        ArrayList<Cell> cellsInColumn = new ArrayList<>();

        int column = cell.getColumn();
        for (int row = 0; row < height; row++) {
            cellsInColumn.add(getCell(row, column));
        }

        return cellsInColumn;
    }
}
