package ar.fiuba.tdd.template.board;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.RelativeClueContent;

import java.util.ArrayList;

public class Region {
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

    public ClueContent getClue() {
        //TODO CAMBIAR ESTO CUANDO LA REGIÓN TENGA UNA CLUE EN VEZ DE UN VALOR.
        return new ClueContent(this.total);
    }

    public int getDiagonalsPartial() {
        int diagonals = 0;
        for (Cell cell: this.cells) {
            ArrayList<RelativeClueContent> relativeClueContents = cell.getPositionContents();
            for (RelativeClueContent relativeClue: relativeClueContents) {
                if (relativeClue.getClue() == this.getClue() &&
                        cell.hasValue(this.getClue().getNumberValue())) {
                    ++diagonals;
                }
            }
        }
        return diagonals;
    }
}