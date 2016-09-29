package ar.fiuba.tdd.template.rules.finders;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;

import java.util.ArrayList;

/**
 * Created by alazraqui on 28/09/2016.
 */
public interface ICellsFinder {
    public ArrayList<Cell> getCellsToValidate(Board board, Cell cell);
}
