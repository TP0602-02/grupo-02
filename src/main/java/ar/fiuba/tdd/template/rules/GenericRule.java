package ar.fiuba.tdd.template.rules;


import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by alazraqui on 25/09/2016.
 */
public abstract class GenericRule {

    public abstract boolean validate(Board board, Cell cell, int numberToAdd);
}
