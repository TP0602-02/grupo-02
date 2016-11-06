package ar.fiuba.tdd.template.rules;


import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;

/**
 * Created by alazraqui on 09/10/2016.
 */
public abstract class ConectionRule extends GenericRule {
    protected BoardIteratorConnections iterator;

    public ConectionRule() {
        this.iterator = new BoardIteratorConnections();
    }

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        Cell nextCell = this.iterator.getNextCell(board, cell, numberToAdd);
        return validateConection(board, cell, nextCell);
    }

    public abstract boolean validateConection(Board board, Cell cell, Cell nextCell);
}
