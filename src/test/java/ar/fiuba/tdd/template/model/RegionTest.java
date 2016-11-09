package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.*;
import ar.fiuba.tdd.template.entity.Coordinate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class RegionTest {

    private Region region;

    @Before
    public void setUp() {
        int total = 10;
        ArrayList<Cell> cells = new ArrayList<>();
        this.region = new Region(cells);
        region.setClue(new ClueContent(total));
    }

    @Test
    public void createRegionWithTotalAndWithoutCells() {
        Assert.assertTrue(this.region.getTotal() == 10);
        Assert.assertTrue(this.region.getCells().size() == 0);
    }

    @Test
    public void addCellToRegion() {
        Cell cell = new CellSingleValue(new Coordinate(5, 5));
        this.region.addCell(cell);
        Assert.assertTrue(this.region.getCells().contains(cell));
    }

    @Test
    public void doesNotAddCellToRegionIfAlreadyHave() {
        Cell cell = new CellSingleValue(new Coordinate(5, 5));
        this.region.addCell(cell);
        this.region.addCell(cell);
        Assert.assertTrue(region.getCells().size() == 1);
    }

    @Test
    public void getOccupiedCellsZeroIfIHaveBlackContent() {
        Cell cell = new CellMultipleValue(new Coordinate(0, 0));
        cell.addContent(new BlackContent());
        region.addCell(cell);
        Assert.assertTrue(region.getOcuppiedCells() == 0);
    }

    @Test
    public void getOccupiedCellsZeroIfIHaveClueContent() {
        Cell cell = new CellMultipleValue(new Coordinate(0, 0));
        cell.addContent(new ClueContent(1));
        region.addCell(cell);
        Assert.assertTrue(region.getOcuppiedCells() == 0);
    }

    @Test
    public void getOccupiedCellsOneIfIHaveValueContent() {
        Cell cell = new CellMultipleValue(new Coordinate(0, 0));
        cell.addContent(new ValueContent(1));
        region.addCell(cell);
        Assert.assertTrue(region.getOcuppiedCells() == 1);
    }

    @Test
    public void getOccupiedCellsOneIfIHaveTwoValueContentsInSameCell() {
        Cell cell = new CellMultipleValue(new Coordinate(0, 0));
        cell.addContent(new ValueContent(1));
        cell.addContent(new ValueContent(2));
        region.addCell(cell);
        Assert.assertTrue(region.getOcuppiedCells() == 1);
    }

    @Test
    public void getOccupiedCellsWithMultipleCells() {
        Cell cell = new CellMultipleValue(new Coordinate(0, 0));
        cell.addContent(new ValueContent(1));
        cell.addContent(new ValueContent(2));
        Cell cell2 = new CellMultipleValue(new Coordinate(1, 0));
        cell2.addContent(new ValueContent(2));
        Cell cell3 = new CellMultipleValue(new Coordinate(2, 0));
        cell3.addContent(new BlackContent());
        region.addCell(cell);
        region.addCell(cell2);
        region.addCell(cell3);
        Assert.assertTrue(region.getOcuppiedCells() == 2);
    }

}
