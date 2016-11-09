package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.region.Region;

public class NoRepeatValueRule extends NumberRule {

    @Override
    public boolean validateRegion(Region region, Cell cell, int numberToAdd) {
        for (Cell actualCell : region.getCells()) {
            if (actualCell != cell && actualCell.getSizeOfContents() > 0) {
                int actualCellValue = actualCell.getFirstContent().getNumberValue();
                if (actualCellValue == numberToAdd) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void initializeTotals(Region region) {
    }
}
