package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by matiaskamien on 15/10/16.
 */
public class RegionTotalBorderRule extends GenericTotalRegionRule {

    //Each region has only one cell.
    @Override
    public boolean validate(Board board) {
        for (Region region: board.getRegions()) {
            Cell regionCell = region.getCells().get(0);
            int numberOfBorders = regionCell.getSummableContents().size();
            int regionTotal = region.getTotal();
            if (regionTotal != this.noClueRestriction && numberOfBorders != regionTotal) {
                return false;
            }
        }
        return true;
    }
}
