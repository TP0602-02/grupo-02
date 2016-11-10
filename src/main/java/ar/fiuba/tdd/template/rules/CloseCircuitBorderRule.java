package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithBorders;

public class CloseCircuitBorderRule extends GenericCloseCircuitRule {

    public CloseCircuitBorderRule() {
        super();
        this.verificator = new CircuitVerificatorWithBorders();
        this.totalRegionRule = new RegionTotalBorderRule();
    }

    //It does not have other validations.
    @Override
    protected boolean checkOtherMethods(Board board) {
        return true;
    }

    @Override
    protected void injectCellToStartVerification(Cell selectedCell) {
        this.verificator.setCell(selectedCell);
    }
}
