package ar.fiuba.tdd.template.rules.validators;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.board.cell.CellContent;
import ar.fiuba.tdd.template.rules.GenericRule;

import java.util.ArrayList;

/**
 * Created by alazraqui on 28/09/2016.
 */
public class MinMaxValidator implements ICellsValidator {

    @Override
    public boolean validate(GenericRule rule, Board board, ArrayList<Cell> cellsToValidate, int numberToAdd, Cell cell) {
        int maxNumber = board.getHeight(); //es cuadrado
        for (Cell actualCell : cellsToValidate) {
            ArrayList<CellContent> actualContents = actualCell.getContents();
            for (CellContent<Integer> actualContent : actualContents) {
                if ((actualContent.getValue() > maxNumber) || (actualContent.getValue()) < 0) {
                    return false;
                }
            }
        }
        return rule.getNextRule().validate(board, cell, numberToAdd);
    }
}
