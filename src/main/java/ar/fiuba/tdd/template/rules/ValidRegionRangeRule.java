package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by alazraqui on 02/11/2016.
 */
public class ValidRegionRangeRule extends NumberRule {
    @Override
    public boolean validateRegion(Region region, Cell cell, int numberToAdd) {
        return numberToAdd > 0 && numberToAdd <= region.getCells().size();
    }

    @Override
    protected void initializeTotals(Region region) {

    }
}
