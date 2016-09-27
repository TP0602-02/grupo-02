package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;

/**
 * Created by alazraqui on 27/09/2016.
 */
public class NumberValidationRule extends GenericRule {
    private int minNumber;
    private int maxNumber;

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        if (numberToAdd < this.minNumber || numberToAdd > this.maxNumber) {
            return false;
        }
        return this.nextRule.validate(board, cell, numberToAdd);
    }

    public NumberValidationRule(int minNumber, int maxNumber) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.nextRule = new NullRule();
    }


}
