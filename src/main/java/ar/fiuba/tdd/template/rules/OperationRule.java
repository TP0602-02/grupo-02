package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.entity.Play;

/**
 * Created by matiaskamien on 08/10/16.
 */
public abstract class OperationRule extends NumberRule {

    protected int regionTotal;
    protected int amountOfCellsInTheRegion;
    protected int amountOfCellsWithValue;
    protected int regionPartial;

    @Override
    public boolean validateRegion(Region region, Play play) {
        for (Cell actualCell : region.getCells()) {
            CellContent firstContentWithValue = actualCell.getFirstEditableContent();
            if (actualCell != play.getSelectedCell() && firstContentWithValue != null) {
                ++this.amountOfCellsWithValue;
                int actualCellValue = firstContentWithValue.getNumberValue();
                this.updateTotals(actualCellValue);
            }
        }
        this.updateTotals(play.getValueOfCell());
        ++this.amountOfCellsWithValue;
        return validate();
    }

    private boolean validate() {
        if (this.regionPartial > this.regionTotal && hasTotalRestriction()) {
            return false;
        }
        if (this.amountOfCellsInTheRegion == this.amountOfCellsWithValue && this.regionPartial < this.regionTotal) {
            return false;
        }
        return true;
    }

    private boolean hasTotalRestriction() {
        return this.regionTotal != -1;
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

    @Override
    public String toString() {
        return "Valor ingresado hace que no se cumpla con el total a alcanzar solicitado por la region";
    }
}
