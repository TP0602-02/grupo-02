package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.entity.Play;

public class NoRepeatValueRule extends NumberRule {

    @Override
    public boolean validateRegion(Region region, Play play) {
        for (Cell actualCell : region.getCells()) {
            if (actualCell != play.getSelectedCell() && actualCell.getSizeOfContents() > 0) {
                int actualCellValue = actualCell.getFirstContent().getNumberValue();
                if (actualCellValue == play.getValueOfCell()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void initializeTotals(Region region) {
    }

    @Override
    public String toString() {
        return "El valor ingresado ya existe en la region";
    }
}
