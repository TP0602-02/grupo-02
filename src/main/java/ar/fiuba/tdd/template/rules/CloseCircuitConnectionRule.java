package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithoutBorders;

/**
 * Created by alazraqui on 12/10/2016.
 */
public class CloseCircuitConnectionRule extends GenericCloseCircuitRule {


    public CloseCircuitConnectionRule() {
        super();
        this.verificator = new CircuitVerificatorWithoutBorders();
        this.totalRegionRule = new RegionTotalConnectionRule();
    }

    @Override
    protected boolean checkOtherMethods(Board board) {
        //TODO Pasar este método a una regla!
        return this.validateAdyacentCells(board);
    }

    private boolean validateAdyacentCells(Board board) {
        for (Region region : board.getRegions()) {
            for (Cell cell : region.getCells()) {
                if (!cell.isSummable()) {
                    if (!verificate(board, cell)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean verificate(Board board, Cell cell) {
        for (Cell actualCell : board.getAdyacentCells(cell)) {
            if (actualCell.isSummable() || board.cellsInSameRegion(cell, actualCell)) {
                return true;
            }
        }
        return false;
    }
}
