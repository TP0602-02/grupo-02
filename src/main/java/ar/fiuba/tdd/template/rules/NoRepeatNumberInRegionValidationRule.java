package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 27/09/16.
 */
public class NoRepeatNumberInRegionValidationRule extends NoRepeatValueValidationRule {

    public NoRepeatNumberInRegionValidationRule() {
        this.nextRule = new NullRule();
    }

    @Override
    public ArrayList<Cell> getCellsToValidate(Board board, Cell cell) {
        ArrayList<Cell> horizontalRegionCells = this.getHorizontalRegionCells(board, cell);
        ArrayList<Cell> verticalRegionCells = this.getVerticalRegionCells(board, cell);
        horizontalRegionCells.addAll(verticalRegionCells);
        return horizontalRegionCells;
    }

    private ArrayList<Cell> getHorizontalRegionCells(Board board, Cell cell) {
        ArrayList<Cell> horizontalCells = new ArrayList<Cell>();
        horizontalCells.addAll(this.getLeftCells(board, cell));
        horizontalCells.addAll(this.getRightCells(board, cell));
        return horizontalCells;
    }

    private ArrayList<Cell> getVerticalRegionCells(Board board, Cell cell) {
        ArrayList<Cell> verticalCells = new ArrayList<Cell>();
        verticalCells.addAll(this.getAboveCells(board, cell));
        verticalCells.addAll(this.getBelowCells(board, cell));
        return verticalCells;
    }

    private ArrayList<Cell> getAboveCells(Board board, Cell cell) {
        ArrayList<Cell> aboveCells = new ArrayList<Cell>();
        while(cell.getColumn() >= 0) {
            Cell cellToCheck = board.getAboveCell(cell);
            if (cellToCheck.isSummable()) {
                aboveCells.add(cellToCheck);
                cell = cellToCheck;
            } else {
                return aboveCells;
            }
        }
        return null;
    }

    private ArrayList<Cell> getBelowCells(Board board, Cell cell) {
        ArrayList<Cell> belowCells = new ArrayList<Cell>();
        while(cell.getColumn() >= 0) {
            Cell cellToCheck = board.getBelowCell(cell);
            if (cellToCheck.isSummable()) {
                belowCells.add(cellToCheck);
                cell = cellToCheck;
            } else {
                return belowCells;
            }
        }
        return null;
    }

    // Template Method ??
    private ArrayList<Cell> getLeftCells(Board board, Cell cell) {
        ArrayList<Cell> leftCells = new ArrayList<Cell>();
        while(cell.getColumn() >= 0) {
            Cell cellToCheck = board.getLeftCell(cell);
            if (cellToCheck.isSummable()) {
                leftCells.add(cellToCheck);
                cell = cellToCheck;
            } else {
                return leftCells;
            }
        }
        return null;
    }

    private ArrayList<Cell> getRightCells(Board board, Cell cell) {
        ArrayList<Cell> rightCells = new ArrayList<Cell>();
        while(cell.getColumn() >= 0) {
            Cell cellToCheck = board.getRightCell(cell);
            if (cellToCheck.isSummable()) {
                rightCells.add(cellToCheck);
                cell = cellToCheck;
            } else {
                return rightCells;
            }
        }
        return null;
    }
}
