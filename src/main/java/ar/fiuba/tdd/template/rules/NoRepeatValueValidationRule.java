package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;

public class NoRepeatValueValidationRule extends GenericRule {

    @Override
    public boolean validateRegion(Region region, Cell cell, int numberToAdd) {
        for (Cell actualCell : region.getCells()) {
            //Revisar el get(0) si hay casos donde pueda haber más valores.
            //int actualCellValue = actualCell.getContents().get(0).getValue().getValueAsInt(); ESTE ESTÁ BIEN.
            if (actualCell != cell && actualCell.getContents().size() > 0) {
                int actualCellValue = (int)actualCell.getContents().get(0).getValue();//Este se va a borrar, cuando se cambie T a GenericValue.
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
