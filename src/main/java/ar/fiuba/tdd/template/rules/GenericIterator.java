package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;

/**
 * Created by matiaskamien on 28/09/16.
 */
public abstract class GenericIterator {

    protected Board board;

    protected GenericIterator() {}

    public abstract boolean hasNext(Cell cell);

    public abstract Cell getNextCell(Cell cell);

    public void setBoard(Board board) {
        this.board = board;
    }

}
