package ar.fiuba.tdd.template.rules.finders;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;

import java.util.ArrayList;

/**
 * Created by alazraqui on 28/09/2016.
 */
public class CellsInRowFinder implements  ICellsFinder{
    @Override
    public ArrayList<Cell> getCellsToValidate(Board board, Cell cell) {
        return board.getRow(cell);
    }
}
