package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.entity.Constants;
import ar.fiuba.tdd.template.entity.Play;

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
    public boolean validate(Board board, Play play) {
        return this.validate(board);
    }

    @Override
    public String toString() {
        return "La cantidad de conecciones debe ser la solicitada por la pista que posee la region en cuestion";
    }
}
