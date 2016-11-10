package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.region.Region;

public class ValidRegionRangeRule extends NumberRule {
    @Override
    public boolean validateRegion(Region region, Cell cell, int numberToAdd) {
        return numberToAdd > 0 && numberToAdd <= region.getCells().size();
    }

    @Override
    protected void initializeTotals(Region region) {

    }

    @Override
    public String toString() {
        return "Valor ingresado fuera de rango entre los valores permitidos";
    }
}
