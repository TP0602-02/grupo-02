package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithoutBorders;

/**
 * Created by alazraqui on 12/10/2016.
 */
public class ConectionInsideBoardRule extends GenericRule {
    CircuitVerificatorWithoutBorders verificator;

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        return verificator.validateDirection(board,cell,numberToAdd);
    }

    public ConectionInsideBoardRule() {
        this.verificator = new CircuitVerificatorWithoutBorders();
    }
}
