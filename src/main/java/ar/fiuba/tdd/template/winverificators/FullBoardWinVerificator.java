package ar.fiuba.tdd.template.winverificators;

import ar.fiuba.tdd.template.board.Board;

/**
 * Created by alazraqui on 13/10/2016.
 */
public class FullBoardWinVerificator extends WinVerificator {
    @Override
    public boolean wonTheGame(Board board) {
        return board.isFull();
    }
}
