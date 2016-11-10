package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;
import ar.fiuba.tdd.template.entity.Play;

public class ConectionInsideBoardRule extends GenericRule {
    BoardIteratorConnections iterator;

    public ConectionInsideBoardRule() {
        this.iterator = new BoardIteratorConnections();
    }

    @Override
    public boolean validate(Board board, Play play) {

        return iterator.validateDirection(board, play.getSelectedCell(), play.getValueOfCell());
    }

    @Override
    public String toString() {
        return "La coneccion ingresada excede los limites del tablero";
    }
}
