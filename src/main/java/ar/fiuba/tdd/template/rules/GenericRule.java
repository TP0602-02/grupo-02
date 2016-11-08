package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

public abstract class GenericRule {

    public abstract boolean validate(Board board, Cell cell, int numberToAdd);
}
