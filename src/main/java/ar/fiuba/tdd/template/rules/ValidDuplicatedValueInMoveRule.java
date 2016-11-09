package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIterator;

import java.util.ArrayList;

/**
 * Created by alazraqui on 02/11/2016.
 */
public abstract class ValidDuplicatedValueInMoveRule extends GenericRule {
    protected BoardIterator iterator = new BoardIterator();
    protected Cell pivotCell;

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        this.pivotCell = cell;
        for (int i = 1; i <= numberToAdd; i++) {
            this.pivotCell = move(board);
            if (this.pivotCell == null) {
                return true;
            }
            if (!this.pivotCell.isEmpty() && numberToAdd == this.pivotCell.getFirstContent().getNumberValue()) {
                return false;
            }
        }
        return true;
    }

    protected abstract Cell move(Board board);
}