package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;

/**
 * Created by alazraqui on 12/10/2016.
 */
public class ConectionInsideBoardRule extends GenericRule {
    BoardIteratorConnections iterator;

    public ConectionInsideBoardRule() {
        this.iterator = new BoardIteratorConnections();
    }

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        return iterator.validateDirection(board, cell, numberToAdd);
    }
}
