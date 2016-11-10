package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.entity.Play;

public class ValidRegionRangeRule extends NumberRule {
    @Override
    public boolean validateRegion(Region region, Play play) {
        return play.getValueOfCell() > 0 && play.getValueOfCell() <= region.getCells().size();
    }

    @Override
    protected void initializeTotals(Region region) {

    }

    @Override
    public String toString() {
        return "Valor ingresado fuera de rango entre los valores permitidos";
    }
}
