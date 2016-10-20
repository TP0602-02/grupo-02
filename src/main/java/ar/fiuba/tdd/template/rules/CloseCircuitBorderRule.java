package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithBorders;

/**
 * Created by matiaskamien on 15/10/16.
 */
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
}
