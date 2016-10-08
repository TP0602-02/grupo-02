package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;

import java.util.ArrayList;

public class NoRepeatValueValidationRule extends GenericRule {

    @Override
    public boolean validateRegion(Region region, Cell cell, int numberToAdd) {
        return true;
    }
}
