package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.CircuitVerificator;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by alazraqui on 09/10/2016.
 */
public abstract class ConectionRule extends GenericRule {
    protected CircuitVerificator circuitVerificator;

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        Cell nextCell = this.circuitVerificator.getNextCell(board,cell,numberToAdd);
        return validateConection(board,cell,nextCell);
    }

    public abstract boolean validateConection(Board board, Cell cell, Cell nextCell);

    public ConectionRule() {
        this.circuitVerificator = new CircuitVerificator();
    }
}
