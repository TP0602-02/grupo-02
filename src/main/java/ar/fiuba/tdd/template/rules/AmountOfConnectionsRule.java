package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;

/**
 * Created by matiaskamien on 02/11/16.
 */
public class AmountOfConnectionsRule extends ConectionRule {

    @Override
    public boolean validateConection(Board board, Cell cell, Cell nextCell) {
        return validClueConnections(cell) && validClueConnections(nextCell);
    }


    private boolean validClueConnections(Cell cell) {
        if (hasClue(cell)) {
            return (cell.getSummableContents().size() == 0);
        }
        return true;
    }

    private boolean hasClue(Cell cell) {
        return (cell.getContents().size() - cell.getSummableContents().size() > 0);
    }
}
