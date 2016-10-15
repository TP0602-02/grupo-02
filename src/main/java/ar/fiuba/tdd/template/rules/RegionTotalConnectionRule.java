package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;

/**
 * Created by matiaskamien on 15/10/16.
 */
public class RegionTotalConnectionRule extends GenericTotalRegionRule{
    @Override
    public boolean validate(Board board) {
        for (Region region : board.getRegions()) {
            int regionTotal = region.getTotal();
            if (regionTotal != this.noClueRestriction && region.getOcuppiedCells() != regionTotal) {
                return false;
            }
        }
        return true;
    }
}
