package ar.fiuba.tdd.template.winverificators;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificator;

/**
 * Created by alazraqui on 13/10/2016.
 */
public abstract class WinVerificator {
    protected CircuitVerificator verificator;

    public void setVerificator(CircuitVerificator verificator) {
        this.verificator = verificator;
    }

    public abstract boolean wonTheGame(Board board);
}
