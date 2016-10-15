package ar.fiuba.tdd.template.board;


import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
import ar.fiuba.tdd.template.entity.Coordinate;

import java.util.ArrayList;

public class Board {

    private ArrayList<ArrayList<Cell>> board;
    private int width;  // number of columns
    private int height; // number of rows
    private String cellType;
    private ArrayList<Region> regions;

    public Board(int height, int width, String cellType) {
        board = new ArrayList<>();
        this.height = height;
        this.width = width;
        this.cellType = cellType;
        CellFactory cellFactory = new CellFactory();
        for (int col = 0; col < width; col++) {
            ArrayList<Cell> inner = new ArrayList<>();
            for (int row = 0; row < height; row++) {
                inner.add(cellFactory.createCell(this.cellType, new Coordinate(row, col)));
            }
            board.add(inner);
        }
        this.regions = new ArrayList<Region>();
    }

    public boolean isFull() {
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                if (this.getCell(row, col).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Region> getRegions() {
        return this.regions;
    }

    public int getWidth() {
        return width;
    }

    public void addRegion(Region region) {
        this.regions.add(region);
    }

    public boolean cellsInSameRegion(Cell cell, Cell nextCell) {
        ArrayList<Region> regions = this.getCellRegions(cell);
        for (Region region : regions) {
            if (region.containsCell(nextCell)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Region> getCellRegions(Cell cell) {
        ArrayList<Region> cellRegions = new ArrayList<Region>();
        for (Region region : this.regions) {
            if (region.getCells().contains(cell)) {
                cellRegions.add(region);
            }
        }
        return cellRegions;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int row, int column) {
        if (validateNumber(row, this.getHeight()) && validateNumber(column, this.getWidth())) {
            return board.get(column).get(row);
        }
        return null;
    }

    private boolean validateNumber(int index, int max) {
        return index >= 0 && index < max;
    }

    public ArrayList<Cell> getAdyacentCells(Cell cell) {
        ArrayList<Cell> adyacents = new ArrayList<Cell>();
        agregate(this.getCell(cell.getRow() - 1, cell.getColumn()), adyacents);
        agregate(this.getCell(cell.getRow() + 1, cell.getColumn()), adyacents);
        agregate(this.getCell(cell.getRow(), cell.getColumn() - 1), adyacents);
        agregate(this.getCell(cell.getRow(), cell.getColumn() + 1), adyacents);
        return adyacents;
    }

    private ArrayList<Cell> agregate(Cell cell, ArrayList<Cell> cells) {
        if (cell != null) {
            cells.add(cell);
        }
        return cells;
    }

    public ArrayList<CellContent> getContents(int row, int column) {
        return getCell(row, column).getContents();
    }

    public void setValue(int row, int column, CellContent content) {
        Cell cell = getCell(row, column);
        cell.setContent(content);
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
