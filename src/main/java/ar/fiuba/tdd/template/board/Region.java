package ar.fiuba.tdd.template.board;

import ar.fiuba.tdd.template.board.cell.model.Cell;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 07/10/16.
 */
public class Region {
    private final ArrayList<Cell> cells;
    private int total;

    public Region(ArrayList<Cell> cells) {
        this.total = 0;
        this.cells = cells;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Cell> getCells() {
        return this.cells;
    }

    public void addCell(Cell cell) {
        if (!this.cells.contains(cell)) {
            this.cells.add(cell);
        }
    }

    public boolean cellBelongsToRegion(Cell cellvecina) {
        //return cells.contains(cellvecina);
        for (Cell cell : cells) {
            if (cell.getColumn() == cellvecina.getColumn() && cell.getRow() == cellvecina.getRow()) {
                return true;
            }
        }
        return false;
    }
}
