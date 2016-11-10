package ar.fiuba.tdd.template.rules;


import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;
import ar.fiuba.tdd.template.entity.Play;


/**
 * Created by alazraqui on 09/10/2016.
 */
public abstract class ConectionRule extends GenericRule {
    protected BoardIteratorConnections iterator;

    public ConectionRule() {
        this.iterator = new BoardIteratorConnections();
    }

    @Override
    public boolean validate(Board board, Play play) {
        Cell nextCell = this.iterator.getNextCell(board,play.getSelectedCell(),play.getValueOfCell());
        return validateConection(board, play.getSelectedCell(), nextCell);
    }

    public abstract boolean validateConection(Board board, Cell cell, Cell nextCell);
}
