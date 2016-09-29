package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by Nicolas on 29/9/2016.
 */
public class NumberValidationRule extends GenericRule {

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        //TODO en vez de 0 deberia pedirle con un get el minValuePermmited al Board
        if (numberToAdd < 0 || numberToAdd > board.getHeight()) {
            return false;
        }
        return this.nextRule.validate(board, cell, numberToAdd);
    }

    public NumberValidationRule() {
        this.nextRule = new NullRule();
    }


}
