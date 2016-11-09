package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.Constants;

public class RegionTotalConnectionRule extends GenericTotalRegionRule {
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

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        return this.validate(board);
    }
}
