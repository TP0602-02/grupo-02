package ar.fiuba.tdd.template.circuitverificator;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.Coordinate;

/**
 * Created by alazraqui on 02/11/2016.
 */
public class BoardIterator {
    public Cell moveUp(Board board, Cell cell) {
        if (cell.getRow() - 1 < 0) {
            return null;
        }
        return board.getCell(new Coordinate(cell.getRow() - 1, cell.getColumn()));
    }

    public Cell moveDown(Board board, Cell cell) {
        if (cell.getRow() + 1 >= board.getHeight() ) {
            return null;
        }
        return board.getCell(new Coordinate(cell.getRow() + 1, cell.getColumn()));
    }

    public Cell moveRight(Board board, Cell cell) {
        if (cell.getColumn() >= board.getWidth()) {
            return null;
        }
        return board.getCell(new Coordinate(cell.getRow(), cell.getColumn() + 1));
    }

    public Cell moveLeft(Board board, Cell cell) {
        if (cell.getColumn() < 0) {
            return null;
        }
        return board.getCell(new Coordinate(cell.getRow(), cell.getColumn() - 1));
    }
}
