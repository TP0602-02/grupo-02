package ar.fiuba.tdd.template.board;

import ar.fiuba.tdd.template.board.cell.model.Cell;

import java.util.ArrayList;

/**
 * Created by martin on 10/12/16.
 */

public class RegionCreator {
    private Board board;
   // public static final String GENERIC_REGION = "GENERIC REGION";


    public RegionCreator(Board board) {
        this.board = board;
    }

    public Region createRegion(Cell topLeft, Cell bottomRight, ArrayList<Cell> exceptions) {
        return getRegion(topLeft,bottomRight,exceptions);
    }

    private Region getRegion(Cell topLeft, Cell bottomRight, ArrayList<Cell> exceptions) {
        ArrayList<Cell> regionCells = new ArrayList<>();
        for ( int coordX = topLeft.getRow(); coordX <= bottomRight.getRow(); coordX++) {
            for ( int coordY = topLeft.getColumn(); coordY <= bottomRight.getColumn(); coordY++) {
                if (!inExceptions(coordX, coordY, exceptions)) {
                    regionCells.add(board.getCell(coordX, coordY)); // same cell as board
                }
            }
        }

        return new Region(regionCells);
    }

    private boolean inExceptions(int coordX, int coordY, ArrayList<Cell> exceptions) {
        for ( Cell exception : exceptions) {
            if ( exception.getRow() == coordX && exception.getColumn() == coordY) {
                return true;
            }
        }
        return false;
    }


}

