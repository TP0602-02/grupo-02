package ar.fiuba.tdd.template.rules;


import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alazraqui on 25/09/2016.
 */
public abstract class GenericRule {

    protected GenericRule nextRule;

    public void setNextRule(GenericRule nextRule) {
        this.nextRule = nextRule;
    }

    public boolean validate(Board board, Cell cell, int numberToAdd) {
        ArrayList<Region> regionsToValidate = board.getRegions(cell);
        for (Region region : regionsToValidate) {
            if (!this.validateRegion(region,numberToAdd,cell)) { //CAMBIAR POR CLASE
                return false;
            }
        }
        return this.nextRule.validate(board, cell, numberToAdd);
    }

    public abstract boolean validateRegion(Region region, Cell cell, int numberToAdd);
}
