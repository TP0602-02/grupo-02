package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;

/**
 * Created by matiaskamien on 15/10/16.
 */
public abstract class GenericTotalRegionRule {

    protected static final int noClueRestriction = -1;

    public abstract boolean validate(Board board);
}
