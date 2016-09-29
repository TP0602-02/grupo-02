package ar.fiuba.tdd.template.rules.finders;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.rules.iterators.GenericIterator;

import java.util.ArrayList;

/**
 * Created by alazraqui on 28/09/2016.
 */
public abstract class RegionFinder implements ICellsFinder {
    protected ArrayList<GenericIterator> iterators = new ArrayList<GenericIterator>();

    public ArrayList<Cell> getCellsToValidate(Board board, Cell cell) {
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
