package ar.fiuba.tdd.template.model;

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
