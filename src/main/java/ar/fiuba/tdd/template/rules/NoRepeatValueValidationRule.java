package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.board.cell.CellContent;

import java.util.ArrayList;

/**
 * Created by alazraqui on 27/09/2016.
 */
public abstract class NoRepeatValueValidationRule extends GenericRule {

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        ArrayList<Cell> cells = getCellsToValidate(board, cell);
        for (Cell actualCell: cells) {
            ArrayList<CellContent> actualContents = actualCell.getContents();
            for (CellContent<Integer> actualContent: actualContents) {
                if (actualContent.getValue() == numberToAdd) {
                    return false;
                }
            }
        }
        return this.nextRule.validate(board,cell,numberToAdd);
    }

    protected abstract ArrayList<Cell> getCellsToValidate(Board board, Cell cell);

}
