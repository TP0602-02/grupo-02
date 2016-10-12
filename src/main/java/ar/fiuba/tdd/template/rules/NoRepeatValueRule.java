package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;

public class NoRepeatValueRule extends NumberRule {

    @Override
    public boolean validateRegion(Region region, Cell cell, int numberToAdd) {
        for (Cell actualCell : region.getCells()) {
            //Revisar el get(0) si hay casos donde pueda haber mas valores.
            //int actualCellValue = actualCell.getContents().get(0).getValue().getValueAsInt(); ESTE ESTa BIEN.
            if (actualCell != cell && actualCell.getContents().size() > 0) {
                int actualCellValue = actualCell.getContents().get(0).getNumberValue();
                if (actualCellValue == numberToAdd) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void initializeTotals(Region region) {}
}
