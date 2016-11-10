package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;


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
        return (cell.getSizeOfContents() - cell.getSummableContents().size() > 0);
    }

    @Override
    public String toString() {
        return "Supera cantidad de conexiones permitidas";
    }
}
