package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.entity.Play;

public abstract class GenericRule {

    public abstract boolean validate(Board board,Play play);
}
