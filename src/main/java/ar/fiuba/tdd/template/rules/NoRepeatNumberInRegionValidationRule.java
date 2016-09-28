package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 28/09/16.
 */
public abstract class NoRepeatNumberInRegionValidationRule extends NoRepeatValueValidationRule {

    protected ArrayList<GenericIterator> iterators = new ArrayList<GenericIterator>();

    protected ArrayList<Cell> getCellsToValidate(Board board, Cell cell) {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        for (GenericIterator iterator: this.iterators) {
            iterator.setBoard(board);
            cells.addAll(this.getSummableCells(board, cell, iterator));
        }
        return cells;
    }

    protected ArrayList<Cell> getSummableCells(Board board, Cell cell, GenericIterator iterator) {
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
