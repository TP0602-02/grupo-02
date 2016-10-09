package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by matiaskamien on 08/10/16.
 */
public abstract class OperationRule extends GenericRule {

    protected int regionTotal;
    protected int amountOfCellsInTheRegion;
    protected int amountOfCellsWithValue;
    protected int regionPartial;

    @Override
    public boolean validateRegion(Region region, Cell cell, int numberToAdd) {
        for (Cell actualCell : region.getCells()) {
            //TODO Revisar el get(0) si hay casos donde pueda haber mas valores.
            //int actualCellValue = actualCell.getContents().get(0).getValue().getValueAsInt(); ESTE ESTa BIEN.
            if (actualCell != cell && actualCell.getContents().size() > 0) {
                ++this.amountOfCellsWithValue;
                int actualCellValue = actualCell.getContents().get(0).getNumberValue();
                this.updateTotals(actualCellValue);
            }
        }
        this.updateTotals(numberToAdd);
        ++this.amountOfCellsWithValue;
        return validate();
    }

    private boolean validate() {
        if (this.regionPartial > this.regionTotal) {
            return false;
        }
        if (this.amountOfCellsInTheRegion == this.amountOfCellsWithValue && this.regionPartial < this.regionTotal) {
            return false;
        }
        return true;
    }

    @Override
    protected void initializeTotals(Region region) {
        this.regionTotal = region.getTotal();
        this.amountOfCellsInTheRegion = region.getCells().size();
        this.amountOfCellsWithValue = 0;
        this.regionPartial = getNeutralNumberForOperation();
    }

    protected abstract int getNeutralNumberForOperation();

    protected abstract void updateTotals(int value);
}
