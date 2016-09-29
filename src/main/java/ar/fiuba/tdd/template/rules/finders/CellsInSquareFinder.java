package ar.fiuba.tdd.template.rules.finders;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;

import java.util.ArrayList;

/**
 * Created by alazraqui on 28/09/2016.
 */
public class CellsInSquareFinder implements ICellsFinder {
    @Override
    public ArrayList<Cell> getCellsToValidate(Board board, Cell cell) {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        int size = board.getHeight();
        int index = (int) Math.sqrt(size);
        int rowFirstCellRegion = ((int) Math.floor(cell.getRow() / (double) index)) * index;
        int columnFirstCellRegion = ((int) Math.floor(cell.getColumn() / (double) index)) * index;
        for (int i = rowFirstCellRegion; i < rowFirstCellRegion + index; i++) {
            for (int j = columnFirstCellRegion; j < columnFirstCellRegion + index; j++) {
                cells.add(board.getCell(i, j));
            }
        }
        return cells;
    }
}

