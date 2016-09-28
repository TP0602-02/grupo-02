package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.BoardIterator;
import ar.fiuba.tdd.template.board.cell.Cell;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 27/09/16.
 */
public class NoRepeatNumberInRegionValidationRule extends NoRepeatValueValidationRule {

    private GenericIterator iterator;

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
        this.iterator = new LeftIterator(board);
        horizontalCells.addAll(this.getRegionCells(board, cell, this.iterator));
        this.iterator = new RightIterator(board);
        horizontalCells.addAll(this.getRegionCells(board, cell, this.iterator));
        //horizontalCells.addAll(this.getLeftCells(board,cell));
        //horizontalCells.addAll(this.getRightCells(board,cell));

        return horizontalCells;
    }

    private ArrayList<Cell> getVerticalRegionCells(Board board, Cell cell) {
        ArrayList<Cell> verticalCells = new ArrayList<Cell>();
        this.iterator = new AboveIterator(board);
        verticalCells.addAll(this.getRegionCells(board, cell, this.iterator));
        this.iterator = new BelowIterator(board);
        verticalCells.addAll(this.getRegionCells(board, cell, this.iterator));
        //verticalCells.addAll(this.getAboveCells(board,cell));
        //verticalCells.addAll(this.getBelowCells(board,cell));
        return verticalCells;
    }

    private ArrayList<Cell> getRegionCells(Board board, Cell cell, GenericIterator iterator) {
        ArrayList<Cell> regionCells = new ArrayList<Cell>();
        while (iterator.hasNext(cell)) {
            Cell cellToCheck = iterator.getNextCell(cell);
            if (cellToCheck.isSummable()) {
                regionCells.add(cellToCheck);
                cell = cellToCheck;
            } else {
                return regionCells;
            }
        }
        return null;
    }

}
