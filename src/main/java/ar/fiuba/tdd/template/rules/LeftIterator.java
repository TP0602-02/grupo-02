package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;

/**
 * Created by matiaskamien on 28/09/16.
 */
public class LeftIterator extends GenericIterator {

    public LeftIterator() {}

    @Override
    public boolean hasNext(Cell cell) {
        return cell.getColumn() >= 0;
    }

    @Override
    public Cell getNextCell(Cell cell) {
        return this.board.getLeftCell(cell);
    }
}
