package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.RegionCreator;
import ar.fiuba.tdd.template.board.RegionFactory;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
import ar.fiuba.tdd.template.board.cell.model.CellSingleValue;

import ar.fiuba.tdd.template.entity.Coordinate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class RegionFactoryTest {

    @Test
    public void createsRegionsWithoutException() {

        Board board = new Board(10, 10, CellFactory.CELL_SINGLE_VALUE);
        RegionCreator regionCreator = new RegionCreator(board);
        Cell topLeft = new CellSingleValue(new Coordinate(0,0));
        Cell bottomRight = new CellSingleValue(new Coordinate(5,5));
        ArrayList<Cell> exceptions = new ArrayList<>();
        Cell exceptionCell = new CellSingleValue(new Coordinate(1,1));
        exceptions.add(exceptionCell);

        Region region = regionCreator.createRegion(topLeft, bottomRight, exceptions);

        //System.out.print("Number of cells should be 35: (6 * 6 - 1) " + region.getCells().size());
        if (region.getCells().contains(exceptionCell)) {
            assert false;
        } else {
            assert true;
        }

    }

    @Test
    public void createsRegionsWithSameCellsAsBoard() {

        Board board = new Board(10, 10, CellFactory.CELL_SINGLE_VALUE);
        RegionCreator regionCreator = new RegionCreator(board);
        Cell topLeft = new CellSingleValue(new Coordinate(0,0));
        Cell bottomRight = new CellSingleValue(new Coordinate(5,5));
        ArrayList<Cell> exceptions = new ArrayList<>();
        Cell exceptionCell = new CellSingleValue(new Coordinate(1,1));
        exceptions.add(exceptionCell);

        Region region = regionCreator.createRegion(topLeft, bottomRight, exceptions);

        if (region.getCells().contains(board.getCell(4, 4))) {
            assert true;
        } else {
            assert false;
        }

    }

}
