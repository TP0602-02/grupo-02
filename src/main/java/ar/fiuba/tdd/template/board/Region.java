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

    public Region() {
        // Empty region
        this.total = 0;
        this.cells = new ArrayList<>();
    }


    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public ArrayList<Cell> getCells() {
        return this.cells;
    }

    public void addCell(Cell cell) {
        if (!this.cells.contains(cell)) {
            this.cells.add(cell);
        }
    }

}
