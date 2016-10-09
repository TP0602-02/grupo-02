package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;

import java.util.ArrayList;

public abstract class NoRepeatValueValidationRule extends GenericRule {

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        ArrayList<Cell> cells = getCellsToValidate(board, cell);
        for (Cell actualCell : cells) {
            ArrayList<CellContent> actualContents = actualCell.getContents();
            for (CellContent actualContent : actualContents) {
                try {
                    if (actualContent.getNumberValue() == numberToAdd) {
                        return false;
                    }
                } catch (ClassCastException e) {
                    System.out.print(this.getClass().toString() + " Can't cast to int: " + actualContent.getValue() + "\n");
                }
            }
        }
        return this.nextRule.validate(board, cell, numberToAdd);
    }

    protected abstract ArrayList<Cell> getCellsToValidate(Board board, Cell cell);

}
