package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;

/**
 * Created by matiaskamien on 28/09/16.
 */
public class RightIterator extends GenericIterator {

    public RightIterator() {
    }

    @Override
    public boolean hasNext(Cell cell) {
        return cell.getColumn() < board.getWidth();
    }

    @Override
    public Cell getNextCell(Cell cell) {
        return board.getRightCell(cell);
    }
}
