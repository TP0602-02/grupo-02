package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.region.Region;

import java.util.ArrayList;

/**
 * Created by alazraqui on 10/10/2016.
 */
public abstract class NumberRule extends GenericRule {
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        ArrayList<Region> regionsToValidate = this.getRegions(board, cell, numberToAdd);
        for (Region region : regionsToValidate) {
            this.initializeTotals(region);
            if (!this.validateRegion(region, cell, numberToAdd)) {
                return false;
            }
        }
        return true;
    }

    protected ArrayList<Region> getRegions(Board board, Cell cell, int numberToAdd) {
        return board.getCellRegions(cell);
    }

    public abstract boolean validateRegion(Region region, Cell cell, int numberToAdd);

    protected abstract void initializeTotals(Region region);
}
