package ar.fiuba.tdd.template.rules.validators;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.board.cell.CellContent;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.validators.ICellsValidator;

import java.util.ArrayList;

/**
 * Created by alazraqui on 28/09/2016.
 */
public class CorrectAddValidator implements ICellsValidator {

    @Override
    public boolean validate(GenericRule rule, Board board, ArrayList<Cell> cellsToValidate, int numberToAdd, Cell cell) {
        int allContents = 0;
        int clueToValidate = (int) cellsToValidate.get(0).getContents().get(0).getValue();
        for (int i = 1; i < cellsToValidate.size();i++) {
            ArrayList<CellContent> actualContents = cellsToValidate.get(i).getContents();
            for (CellContent<Integer> actualContent : actualContents) {
                allContents += actualContent.getValue();
            }
        }
        if ((allContents != clueToValidate && board.getWidth() == cellsToValidate.size()) || (allContents > clueToValidate)) {
            return false;
        }
        return rule.getNextRule().validate(board, cell, numberToAdd);
    }
}
