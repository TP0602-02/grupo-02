package ar.fiuba.tdd.template.circuitverificator;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.entity.Constants;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 18/10/16.
 */
public class CircuitVerificatorWithDiagonals extends CircuitVerificator {

    private ArrayList<Cell> circuitCells = new ArrayList<Cell>();
    private Cell cell = null;

    //Returns false if there is a closed circuit.
    @Override
    public boolean isCircuitClosed(Board board) {
        this.cleanCircuitCells();
        ArrayList<CellContent> corners = this.cell.getSummableContents();
        for (CellContent corner: corners) {
            return this.checkCircuit(board, this.cell, corner, null);
        }
        return true;
    }

    private boolean checkCircuit(Board board, Cell cell, CellContent corner, Cell previousCell) {
        if (!this.addCellToTheCircuit(cell)) {
            return false;
        }
        return this.checkLimitCells(board, cell, corner, previousCell);
    }

    private boolean checkLimitCells(Board board, Cell cell, CellContent corner, Cell previousCell) {
        ArrayList<Cell> limitCells = this.getLimitCells(board, cell, corner);
        for (Cell limitCell: limitCells) {
            if (limitCell == previousCell) {
                continue;
            } else {
                if (!this.checkCellClosedCircuit(board, limitCell, cell)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkCellClosedCircuit(Board board, Cell limitCell, Cell cell) {
        ArrayList<CellContent> corners = limitCell.getSummableContents();
        for (CellContent limitCellCorner: corners) {
            if (!this.isFirstCorner(limitCellCorner, corners)) {
                this.circuitCells.remove(limitCell);
            }
            if (!this.checkCircuit(board, limitCell, limitCellCorner, cell)) {
                return false;
            }
        }
        return true;
    }

    private boolean isFirstCorner(CellContent limitCellCorner, ArrayList<CellContent> corners) {
        return limitCellCorner == corners.get(0);
    }

    private int getOppositeCorner(CellContent corner) {
        return this.iterator.getOppositeCorner(corner);
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

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
