package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;

import java.util.ArrayList;

/**
 * Created by alazraqui on 27/09/2016.
 */
public class NoRepeatNumberInColumnValidationRule extends NoRepeatValueValidationRule {

    @Override
    public ArrayList<Cell> getCellsToValidate(Board board, Cell cell) {
        return board.getColumn(cell);
    }
}
