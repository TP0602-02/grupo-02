package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.entity.Constants;
import ar.fiuba.tdd.template.entity.Play;

public class RegionTotalBorderRule extends GenericTotalRegionRule {

    //Each region has only one cell.
    @Override
    public boolean validate(Board board) {
        for (Region region : board.getRegions()) {
            Cell regionCell = region.getCells().get(0);
            int numberOfBorders = regionCell.getSummableContents().size();
            int regionTotal = region.getTotal();
            if (regionTotal != Constants.NO_CLUE_RESTRICTION && numberOfBorders != regionTotal) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean validate(Board board,Play play) {
        return this.validate(board);
    }

    @Override
    public String toString() {
        return "La cantidad de bordes debe ser la solicitada por la pista que posee la region en cuestion";
    }
}
