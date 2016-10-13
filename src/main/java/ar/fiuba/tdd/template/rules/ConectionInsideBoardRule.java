package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.CircuitVerificator;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by alazraqui on 12/10/2016.
 */
public class ConectionInsideBoardRule extends GenericRule {
    CircuitVerificator verificator;

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        return verificator.validateDirection(board,cell,numberToAdd);
    }

    public ConectionInsideBoardRule() {
        this.verificator = new CircuitVerificator();
    }
}
