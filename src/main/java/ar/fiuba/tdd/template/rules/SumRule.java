package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by matiaskamien on 08/10/16.
 */
public class SumRule extends GenericRule {

    private int regionTotal;
    private int amountOfCellsInTheRegion;
    private int amountOfCellsWithValue;
    private int partial_sum;

    @Override
    public boolean validateRegion(Region region, Cell cell, int numberToAdd) {
        for (Cell actualCell : region.getCells()) {
            //Revisar el get(0) si hay casos donde pueda haber más valores.
            //int actualCellValue = actualCell.getContents().get(0).getValue().getValueAsInt(); ESTE ESTÁ BIEN.
            if (actualCell != cell && actualCell.getContents().size() > 0) {
                ++this.amountOfCellsWithValue;
                int actualCellValue = (int)actualCell.getContents().get(0).getValue();//Este se va a borrar, cuando se cambie T a GenericValue.
                this.partial_sum += actualCellValue;
            }
        }
        if (this.partial_sum > this.regionTotal) {
            return false;
        }
        if (this.amountOfCellsInTheRegion == this.amountOfCellsWithValue &&
                this.partial_sum < this.regionTotal) {
            return false;
        }
        return true;
    }

    @Override
    protected void initializeTotals(Region region) {
        this.regionTotal = region.getTotal();
        this.amountOfCellsInTheRegion = region.getCells().size();
        this.amountOfCellsWithValue = 0;
        this.partial_sum = 0;
    }
}
