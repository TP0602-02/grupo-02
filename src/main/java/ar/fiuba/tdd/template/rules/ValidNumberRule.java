package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by alazraqui on 16/10/2016.
 */
public class ValidNumberRule extends GenericRule {
    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        return (numberToAdd > 0 && numberToAdd < board.getHeight());
    }
}
