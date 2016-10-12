package ar.fiuba.tdd.template.rules;


import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alazraqui on 25/09/2016.
 */
public abstract class GenericRule {

    public abstract boolean validate(Board board, Cell cell, int numberToAdd);
}
