package ar.fiuba.tdd.template.board;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.Coordinate;

import java.util.ArrayList;

public class RegionCreator {
    private Board board;

    public RegionCreator(Board board) {
        this.board = board;
    }

    public Region createRegion(Cell topLeft, Cell bottomRight, ArrayList<Cell> exceptions) {
        return getRegion(topLeft, bottomRight, exceptions);
    }

    private Region getRegion(Cell topLeft, Cell bottomRight, ArrayList<Cell> exceptions) {
        ArrayList<Cell> regionCells = new ArrayList<>();
        for (int coordX = topLeft.getRow(); coordX <= bottomRight.getRow(); coordX++) {
            for (int coordY = topLeft.getColumn(); coordY <= bottomRight.getColumn(); coordY++) {
                Coordinate coordinate = new Coordinate(coordX, coordY);
                if (!inExceptions(coordinate, exceptions)) {
                    regionCells.add(board.getCell(coordinate)); // same cell as board
                }
            }
        }

        return new Region(regionCells);
    }

    private boolean inExceptions(Coordinate coordinate, ArrayList<Cell> exceptions) {
        for (Cell exception : exceptions) {
            if (exception.getRow() == coordinate.getRow() && exception.getColumn() == coordinate.getColumn()) {
                return true;
            }
        }
        return false;
    }


}

