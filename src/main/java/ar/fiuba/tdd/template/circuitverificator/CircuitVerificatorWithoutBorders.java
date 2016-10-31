package ar.fiuba.tdd.template.circuitverificator;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;

/**
 * Created by matiaskamien on 09/10/16.
 */


public class CircuitVerificatorWithoutBorders extends CircuitVerificator {

    public CircuitVerificatorWithoutBorders() {
        this.firstCell = null;
        this.amountOfCellsInTheCircuit = 0;
    }

    public int getAmountOfCellsInTheCircuit() {
        return this.amountOfCellsInTheCircuit;
    }

    public boolean isCircuitClosed(Board board) {
        this.amountOfCellsInTheCircuit = 0;
        this.firstCell = this.getFirstCellWithValue(board);
        if (this.firstCell != null) {
            ++this.amountOfCellsInTheCircuit;
            int firstCellDirection = this.firstCell.getContents().get(0).getNumberValue();
            return checkCircuit(this.firstCell, firstCellDirection, board);
        } else {
            return false;
        }
    }

    private boolean checkCircuit(Cell cell, int direction, Board board) {
        Cell nextCell = this.iterator.getNextCell(board, cell, direction);
        if (nextCell == null) {
            return false;
        }
        ++this.amountOfCellsInTheCircuit;
        if (nextCell == this.firstCell) {
            --this.amountOfCellsInTheCircuit;
            this.isClose = true;
            return true;
        } else {
            int nextCellDirection = this.getNextDirection(nextCell, direction);
            if (nextCellDirection != -1) {
                return this.checkCircuit(nextCell, nextCellDirection, board);
            }
            return false;
        }
    }

    private int getNextDirection(Cell nextCell, int direction) {
        int oppositeDirection = this.iterator.getOppositeDirection(direction);
        for (CellContent cellContent : nextCell.getContents()) {
            if (cellContent.getNumberValue() != oppositeDirection) {
                return cellContent.getNumberValue();
            }
        }
        return -1;
    }
}
