package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;

/**
 * Created by matiaskamien on 02/11/16.
 */
public class AmountOfConnectionsRule extends GenericRule {
    BoardIteratorConnections iterator = new BoardIteratorConnections();

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        Cell nextCell = this.iterator.getNextCell(board, cell, numberToAdd);
        return ((this.clueWithoutConnections(cell) && this.clueWithoutConnections(nextCell)));
    }


    private boolean clueWithoutConnections(Cell cell) {
        return (!cell.isSummable() && cell.getSummableContents().size() == 0);
    }
}
