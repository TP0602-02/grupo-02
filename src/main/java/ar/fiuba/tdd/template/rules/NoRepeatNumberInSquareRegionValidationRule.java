package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

import java.util.ArrayList;

public class NoRepeatNumberInSquareRegionValidationRule extends NoRepeatValueValidationRule {

    @Override
    protected ArrayList<Cell> getCellsToValidate(Board board, Cell cell) {
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

    public NoRepeatNumberInSquareRegionValidationRule() {
        this.nextRule = new NullRule();
    }
}
