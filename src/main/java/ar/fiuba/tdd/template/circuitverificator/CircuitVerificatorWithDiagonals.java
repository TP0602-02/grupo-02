package ar.fiuba.tdd.template.circuitverificator;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matiaskamien on 18/10/16.
 */
public class CircuitVerificatorWithDiagonals extends CircuitVerificator {

    private ArrayList<Cell> circuitCells = new ArrayList<Cell>();
    private Cell cell = null;

    //Returns true if there is a closed circuit.
    @Override
    public boolean isCircuitClosed(Board board) {
        this.cleanCircuitCells();
        List<CellContent> corners = this.cell.getSummableContents();
        for (CellContent corner: corners) {
            this.cleanCircuitCells();
            if (!this.isCircuitOpen(board, this.cell, corner, null)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCircuitOpen(Board board, Cell cell, CellContent corner, Cell previousCell) {
        if (!this.addCellToTheCircuit(cell)) {
            return false;
        }
        return this.checkLimitCells(board, cell, corner, previousCell);
    }

    private boolean checkLimitCells(Board board, Cell cell, CellContent corner, Cell previousCell) {
        ArrayList<Cell> limitCells = this.getLimitCells(board, cell, corner);
        if (!limitCells.contains(previousCell)) {
            for (Cell limitCell: limitCells) {
                if (!this.checkCellClosedCircuit(board, limitCell, cell, corner)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkCellClosedCircuit(Board board, Cell limitCell, Cell cell, CellContent previousCorner) {
        List<CellContent> corners = this.getCorners(limitCell);
        for (CellContent limitCellCorner: corners) {
            if (this.cellsAreConnected(cell, limitCell)) {
                if (!this.isFirstCorner(limitCellCorner, corners)) {
                    this.circuitCells.remove(limitCell);
                }
                if (!this.isCircuitOpen(board, limitCell, limitCellCorner, cell)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<CellContent> getCorners(Cell limitCell) {
        List<CellContent> corners = new ArrayList<CellContent>();
        List<CellContent> values = limitCell.getSummableContents();
        if (values.size() == 0) {
            return corners;
        }
        switch (values.get(0).getValue()) {
            case "/": corners.add(new ValueContent("2"));
                      corners.add(new ValueContent("3"));
                      return corners;
            case "\\": corners.add(new ValueContent("1"));
                       corners.add(new ValueContent("4"));
                       return corners;
            default: return values;
        }
    }

    private boolean cellsAreConnected(Cell limitCell, Cell cell) {
        return this.iterator.cellsAreConnected(limitCell, cell);
    }

    private boolean isFirstCorner(CellContent limitCellCorner, List<CellContent> corners) {
        return limitCellCorner == corners.get(0);
    }

    private ArrayList<Cell> getLimitCells(Board board, Cell cell, CellContent corner) {
        return this.iterator.getLimitCells(board, cell, corner);
    }

    private boolean addCellToTheCircuit(Cell cell) {
        if (!this.circuitCells.contains(cell)) {
            this.circuitCells.add(cell);
            return true;
        }
        return false;
    }

    private void cleanCircuitCells() {
        this.circuitCells.clear();
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
