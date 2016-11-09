package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

public class ValidNumberRule extends GenericRule {
    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        return (numberToAdd > 0 && numberToAdd < board.getHeight());
    }
}
