package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by matiaskamien on 08/10/16.
 */
public class SumRule extends GenericRule {
    @Override
    public boolean validateRegion(Region region, Cell cell, int numberToAdd) {
       /* int regionTotal = region.getTotal();
        int amountOfCellsInTheRegion = region.getCells().size();
        int amountOfCellsWithValue = 0;
        int partial_sum = 0;
        for (Cell actualCell : region.getCells()) {
            //Revisar el get(0) si hay casos donde pueda haber más valores.
            //int actualCellValue = actualCell.getContents().get(0).getValue().getValueAsInt(); ESTE ESTÁ BIEN.
            if (actualCell != cell && actualCell.getContents().size() > 0) {
                ++amountOfCellsWithValue;
                int actualCellValue = (int)actualCell.getContents().get(0).getValue();//Este se va a borrar, cuando se cambie T a GenericValue.
                partial_sum += actualCellValue;
            }
        }*/
        return true;
    }
}
