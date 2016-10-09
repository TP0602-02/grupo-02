package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellSingleValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 07/10/16.
 */
public class RegionTest {

    private Region region;

    @Before
    public void setUp() {
        int total = 10;
        ArrayList<Cell> cells = new ArrayList<Cell>();
        this.region = new Region(cells);
    }

    @Test
    public void createRegionWithTotalAndWithoutCells() {
        Assert.assertTrue(this.region.getTotal() == 10);
        Assert.assertTrue(this.region.getCells().size() == 0);
    }

    @Test
    public void addCellToRegion() {
        Cell cell = new CellSingleValue(5,5);
        this.region.addCell(cell);
        Assert.assertTrue(this.region.getCells().contains(cell));
    }

    @Test
    public void doesNotAddCellToRegionIfAlreadyHave() {
        Cell cell = new CellSingleValue(5,5);
        this.region.addCell(cell);
        this.region.addCell(cell);
        Assert.assertTrue(region.getCells().size() == 1);
    }
}
