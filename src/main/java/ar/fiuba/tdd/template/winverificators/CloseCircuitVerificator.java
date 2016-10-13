package ar.fiuba.tdd.template.winverificators;

import ar.fiuba.tdd.template.CircuitVerificator;
import ar.fiuba.tdd.template.board.Board;

/**
 * Created by alazraqui on 13/10/2016.
 */
public class CloseCircuitVerificator extends WinVerificator {
    private CircuitVerificator verificator;

    @Override
    public boolean wonTheGame(Board board) {
        return this.verificator.isCircuitClosed(board);
    }

    public CloseCircuitVerificator() {
        this.verificator = new CircuitVerificator();
    }
}
