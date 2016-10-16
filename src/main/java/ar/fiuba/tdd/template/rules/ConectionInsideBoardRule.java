package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithoutBorders;

/**
 * Created by alazraqui on 12/10/2016.
 */
public class ConectionInsideBoardRule extends GenericRule {
    BoardIteratorConnections iterator;

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        return iterator.validateDirection(board,cell,numberToAdd);
    }

    public ConectionInsideBoardRule() {
        this.iterator = new BoardIteratorConnections();
    }
}
