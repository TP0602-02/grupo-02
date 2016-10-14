package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by alazraqui on 10/10/2016.
 */
public class CellHasValidConectionsRule extends ConectionRule {
    private static final int MAX_CONECTIONS_CELL = 2;

    @Override
    public boolean validateConection(Board board, Cell cell, Cell nextCell) {
        return (validateConectionsInCell(cell) && validateConectionsInCell(nextCell));
    }

    private boolean validateConectionsInCell(Cell cell) {
        return cell.getQuantityOfValues() < 2 ;
    }
}
