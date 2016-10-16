package ar.fiuba.tdd.template.winverificators;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithoutBorders;

/**
 * Created by alazraqui on 13/10/2016.
 */
public class CloseCircuitVerificator extends WinVerificator {
    private CircuitVerificatorWithoutBorders verificator;

    @Override
    public boolean wonTheGame(Board board) {
        return this.verificator.isCircuitClosed(board);
    }

    public CloseCircuitVerificator() {
        this.verificator = new CircuitVerificatorWithoutBorders();
    }
}
