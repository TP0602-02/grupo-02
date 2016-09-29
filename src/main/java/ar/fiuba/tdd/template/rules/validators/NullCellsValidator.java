package ar.fiuba.tdd.template.rules.validators;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.rules.GenericRule;

import java.util.ArrayList;

/**
 * Created by alazraqui on 28/09/2016.
 */
public class NullCellsValidator implements ICellsValidator {

    @Override
    public boolean validate(GenericRule rule, Board board, ArrayList<Cell> cellsToValidate, int numberToAdd, Cell cell) {
        return true;
    }
}
