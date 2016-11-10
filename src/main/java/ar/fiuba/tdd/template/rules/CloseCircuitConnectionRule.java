package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.region.Region;
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
        return this.validateAdyacentCells(board);
    }

    @Override
    protected void injectCellToStartVerification(Cell selectedCell) {

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

    // TODO: ojo, para qué están las interfaces si luego usan un método como 'isSummable' para chequear el tipo de celda?
    private boolean verificate(Board board, Cell cell) {
        for (Cell actualCell : board.getAdyacentCells(cell)) {
            if (actualCell.isSummable() || board.cellsInSameRegion(cell, actualCell)) {
                return true;
            }
        }
        return false;
    }
}
