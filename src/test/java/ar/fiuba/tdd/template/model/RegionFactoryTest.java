package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
import ar.fiuba.tdd.template.board.cell.model.CellSingleValue;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.board.region.RegionJson;
import ar.fiuba.tdd.template.entity.Coordinate;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class RegionFactoryTest {

    @Test
    public void createsRegionsWithoutException() {
        Board board = new Board(10, 10, CellFactory.CELL_SINGLE_VALUE);
        Cell topLeft = new CellSingleValue(new Coordinate(0, 0));
        Cell bottomRight = new CellSingleValue(new Coordinate(5, 5));
        ArrayList<Cell> exceptions = new ArrayList<>();
        Cell exceptionCell = new CellSingleValue(new Coordinate(1, 1));
        exceptions.add(exceptionCell);
        RegionJson regionJson = new RegionJson(topLeft, bottomRight, exceptions, false);
        Region region = board.createRegion(regionJson);
        //System.out.print("Number of cells should be 35: (6 * 6 - 1) " + region.getCells().size());
        Assert.assertFalse(region.getCells().contains(exceptionCell));
    }

    @Test
    public void createsRegionsWithSameCellsAsBoard() {
        Board board = new Board(10, 10, CellFactory.CELL_SINGLE_VALUE);
        Cell topLeft = new CellSingleValue(new Coordinate(0, 0));
        Cell bottomRight = new CellSingleValue(new Coordinate(5, 5));
        ArrayList<Cell> exceptions = new ArrayList<>();
        Cell exceptionCell = new CellSingleValue(new Coordinate(1, 1));
        exceptions.add(exceptionCell);
        RegionJson regionJson = new RegionJson(topLeft, bottomRight, exceptions, false);
        Region region = board.createRegion(regionJson);
        Assert.assertTrue(region.getCells().contains(board.getCell(new Coordinate(4, 4))));

        //FIXME: use Assert.assertTrue!
        if (region.getCells().contains(board.getCell(new Coordinate(4, 4)))) {
            assert true;
        } else {
            assert false;
        }

    }

}
