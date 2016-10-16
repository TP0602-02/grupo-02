package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.Constants;

/**
 * Created by alazraqui on 15/10/2016.
 */
public class NumberOfBordersInRegionRule extends NumberRule {

    private static final int noClueRestriction = -1;

    @Override
    public boolean validateRegion(Region region, Cell cell, int numberToAdd) {
        return (cell.getSummableContents().size() < region.getTotal()) || (region.getTotal() == Constants.NO_CLUE_RESTRICTION);
    }

    @Override
    protected void initializeTotals(Region region) {

    }
}
