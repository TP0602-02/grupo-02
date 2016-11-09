package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;

public class NoRepeatNumberInCell extends GenericRule {
    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        for (CellContent content : cell.getSummableContents()) {
            if (content.getNumberValue() == numberToAdd) {
                return false;
            }
        }
        return true;
    }
}
