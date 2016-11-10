package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

public class CellHasValidConectionsRule extends ConectionRule {
    private static final int MAX_CONNECTIONS_CELL = 2;

    @Override
    public boolean validateConection(Board board, Cell cell, Cell nextCell) {
        return (validateConectionsInCell(cell) && validateConectionsInCell(nextCell));
    }

    private boolean validateConectionsInCell(Cell cell) {
        return cell.getQuantityOfValues() < MAX_CONNECTIONS_CELL;
    }

    @Override
    public String toString() {
        return "La celda contiene el maximo de conexiones permitidas, es decir + " + MAX_CONNECTIONS_CELL;
    }
}
