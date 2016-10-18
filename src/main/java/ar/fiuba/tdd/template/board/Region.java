package ar.fiuba.tdd.template.board;

import ar.fiuba.tdd.template.board.cell.model.Cell;

import java.util.ArrayList;

public class Region {
    private static final String COORDENADAX = " X: ";
    private static final String COORDENADAY = " Y: ";
    private static final String REGION = "REGION";
    private static final String TOTAL = "TOTAL: ";
    private final ArrayList<Cell> cells;
    private int total;

    public Region(ArrayList<Cell> cells) {
        this.total = -1;
        this.cells = cells;
    }

    public int getOcuppiedCells() {
        int total = 0;
        for (Cell cell : this.cells) {
            if (cell.isSummable()) {
                total += 1;
            }
        }
        return total;
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

    public boolean containsCell(Cell cell) {
        return this.cells.contains(cell);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        String saltoLinea = "\n";
        buffer.append(REGION).append(saltoLinea);
        for (Cell cell : this.cells) {
            buffer.append(COORDENADAX).append(cell.getColumn()).append(COORDENADAY)
                    .append(cell.getRow()).append(saltoLinea);
        }
        if (this.total != -1) {
            buffer.append(TOTAL).append(this.total).append(saltoLinea);
        }
        return buffer.toString();
    }
}