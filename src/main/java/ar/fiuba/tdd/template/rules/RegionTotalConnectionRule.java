package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.entity.Constants;

/**
 * Created by matiaskamien on 15/10/16.
 */
public class RegionTotalConnectionRule extends GenericTotalRegionRule{
    @Override
    public boolean validate(Board board) {
        for (Region region : board.getRegions()) {
            int regionTotal = region.getTotal();
            if (regionTotal != Constants.NO_CLUE_RESTRICTION && region.getOcuppiedCells() != regionTotal) {
                return false;
            }
        }
        return true;
    }
}
