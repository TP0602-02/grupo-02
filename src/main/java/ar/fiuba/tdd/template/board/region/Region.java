package ar.fiuba.tdd.template.board.region;

import ar.fiuba.tdd.template.board.cell.model.BlackContent;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.RelativeClueContent;

import java.util.ArrayList;
import java.util.List;

public class Region {
    private static final String COORDENADAX = " X: ";
    private static final String COORDENADAY = " Y: ";
    private static final String REGION = "REGION";
    private static final String TOTAL = "TOTAL: ";
    private final ArrayList<Cell> cells;
    private CellContent clue;
    private boolean graficable;

    public Region(ArrayList<Cell> cells) {
        this.clue = new BlackContent();
        this.cells = cells;
    }

    public boolean isGraficable() {
        return this.graficable;

    }

    public void setGraficable(boolean graficable) {
        this.graficable = graficable;
    }

    public CellContent getClue() {
        return clue;
    }

    public void setClue(CellContent clue) {
        this.clue = clue;
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

    public int getTotal() {
        return this.clue.getNumberValue();
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
            buffer.append(COORDENADAX).append(cell.getRow()).append(COORDENADAY)
                    .append(cell.getColumn()).append(saltoLinea);
        }
        if (getTotal() != -1) {
            buffer.append(TOTAL).append(getTotal()).append(saltoLinea);
        }
        return buffer.toString();
    }

    public int getDiagonalsPartial() {
        int diagonals = 0;
        for (Cell cell : this.cells) {
            ArrayList<RelativeClueContent> relativeClueContents = cell.getPositionContents();
            for (RelativeClueContent relativeClue : relativeClueContents) {
                if (relativeClue.getClue() == this.clue && this.cellHasValue(cell, relativeClue)) {
                    ++diagonals;
                }
            }
        }
        return diagonals;
    }

    private boolean cellHasValue(Cell cell, RelativeClueContent relativeClue) {
        List<CellContent> summableContents = cell.getSummableContents();
        if (summableContents.size() == 0) {
            return false;
        }
        ArrayList<Integer> values = this.getCellValues(summableContents);
        if (values.contains(relativeClue.getNumberValue())) {
            return true;
        }
        return false;
    }

    private ArrayList<Integer> getCellValues(List<CellContent> summableContents) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        switch (summableContents.get(0).getValue()) {
            case "/": values.add(2);
                      values.add(3);
                      return values;
            case "\\": values.add(1);
                       values.add(4);
                       return values;
            default: return values;
        }
    }
}