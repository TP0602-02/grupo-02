package ar.fiuba.tdd.template.rules;


import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;

import java.util.ArrayList;

/**
 * Created by alazraqui on 25/09/2016.
 */
public abstract class GenericRule {

    public boolean validate(Board board, Cell cell, int numberToAdd) {
        ArrayList<Region> regionsToValidate = board.getCellRegions(cell);
        for (Region region : regionsToValidate) {
            this.initializeTotals(region);
            if (!this.validateRegion(region, cell, numberToAdd)) { //CAMBIAR POR CLASE
                return false;
            }
        }
        return true;
    }

    public abstract boolean validateRegion(Region region, Cell cell, int numberToAdd);

    protected abstract void initializeTotals(Region region);
}
