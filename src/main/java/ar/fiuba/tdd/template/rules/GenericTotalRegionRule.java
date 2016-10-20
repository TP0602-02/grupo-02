package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;

/**
 * Created by matiaskamien on 15/10/16.
 */
public abstract class GenericTotalRegionRule extends GenericRule {

    protected abstract boolean validate(Board board);
}
