package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.entity.Constants;

import java.util.ArrayList;

public class NumberOfBordersInRegionRule extends ConectionRule {

    private boolean exceedsNumberOfBorders(Board board, Cell cell) {
        ArrayList<Region> regionsToValidate = board.getCellRegions(cell);
        for (Region region : regionsToValidate) {
            int regionTotal = region.getTotal();
            int cellAmountOfBorders = cell.getSummableContents().size();
            if (regionTotal != Constants.NO_CLUE_RESTRICTION && cellAmountOfBorders >= regionTotal) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validateConection(Board board, Cell cell, Cell nextCell) {
        return !this.exceedsNumberOfBorders(board, cell) && !this.exceedsNumberOfBorders(board, nextCell);
    }

    @Override
    public String toString() {
        return "Cantidad de bordes no es la indicada por la pista en la region.";
    }
}
