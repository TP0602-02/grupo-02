package ar.fiuba.tdd.template.rules;


import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;

/**
 * Created by alazraqui on 25/09/2016.
 */
public abstract class GenericRule {

    protected GenericRule nextRule;

    public void setNextRule(GenericRule nextRule) {
        this.nextRule = nextRule;
    }

    public abstract boolean validate(Board board, Cell cell, int numberToAdd);
}
