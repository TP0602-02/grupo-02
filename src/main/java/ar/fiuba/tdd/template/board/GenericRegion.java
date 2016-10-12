package ar.fiuba.tdd.template.board;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;

import java.util.ArrayList;

import static ar.fiuba.tdd.template.board.cell.model.CellFactory.CELL_SINGLE_VALUE;

/**
 * Created by martin on 10/12/16.
 */
public class GenericRegion extends Region {


    GenericRegion() {
        super(null);
    }

    public Region getRegion(Cell topLeft, Cell bottomRight, ArrayList<Cell> exceptions) {
        ArrayList<Cell> regionCells = new ArrayList<>();

        for ( int coordX = topLeft.getRow(); coordX <= bottomRight.getRow(); coordX++) {
            for ( int coordY = topLeft.getColumn(); coordY <= bottomRight.getColumn(); coordY++) {
                boolean inExceptions = false;
                for ( Cell exception : exceptions) {
                    if ( exception.getRow() == coordX && exception.getColumn() == coordY) {
                        inExceptions = true;
                        break;
                    }
                }
                if (!inExceptions) {
                    regionCells.add(new CellFactory().createCell(CELL_SINGLE_VALUE, coordX, coordY));
                }
            }
        }

        return new Region(regionCells);
    }
}
