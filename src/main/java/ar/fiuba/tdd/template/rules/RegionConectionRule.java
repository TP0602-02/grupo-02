package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by alazraqui on 12/10/2016.
 */
public abstract class RegionConectionRule extends ConectionRule {
    @Override
    public boolean validateConection(Board board, Cell cell, Cell nextCell) {
        if (board.cellsInSameRegion(cell, nextCell)) {
            return validationInSameRegion(board, cell, nextCell);
        }
        return validationInMoreThanOneRegion(board, cell, nextCell);
    }

    protected abstract boolean validationInMoreThanOneRegion(Board board, Cell cell, Cell nextCell);

    protected abstract boolean validationInSameRegion(Board board, Cell cell, Cell nextCell);
}
