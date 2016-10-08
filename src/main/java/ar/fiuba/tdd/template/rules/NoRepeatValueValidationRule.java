package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;

import java.util.ArrayList;

public abstract class NoRepeatValueValidationRule extends GenericRule {

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        ArrayList<Region> regionsToValidate = board.getRegions(cell);
        for (Region actualRegion : regionsToValidate) {
            ArrayList<CellContent> actualContents = actualCell.getContents();
            for (CellContent<Integer> actualContent : actualContents
                    if (actualContent.getValue() == numberToAdd) {
                        return false;
                    }
            }
        }
        return this.nextRule.validate(board, cell, numberToAdd);
    }
}
