package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.cell.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Nicolas on 7/10/2016.
 */
public class CellTest {

    private CellFactory cellFactory;

    @Before
    public void setUp() {
        cellFactory = new CellFactory();
    }

    @Test
    public void createCellSingleValue() {
        Assert.assertTrue(cellFactory.createCell(CellFactory.CELL_SINGLE_VALUE, 1, 1) instanceof CellSingleValue);
    }

    @Test
    public void cellMultipleValueIsTheDefaultCellToCreate() {
        Assert.assertTrue(cellFactory.createCell("djksnld", 1, 1) instanceof CellMultipleValue);
        Assert.assertTrue(cellFactory.createCell("", 1, 1) instanceof CellMultipleValue);
    }


    @Test
    public void cellSingleValueAllowOnlyOneValueToAdd() {
        Cell cell = cellFactory.createCell(CellFactory.CELL_SINGLE_VALUE, 1, 1);
        int firstValueToAdd = 3;
        int secondValueToAdd = 14;
        CellContent cellContent1 = new ValueContent(firstValueToAdd);
        CellContent cellContent2 = new ValueContent(secondValueToAdd);
        cell.setContent(cellContent1);
        cell.setContent(cellContent2);
        Assert.assertEquals(cell.getContents().size(), 1);
        Assert.assertEquals(cell.getContents().get(0).getNumberValue(), secondValueToAdd);

    }


    @Test
    public void cellMultipleValueAllowMultipleValueToAdd() {
        Cell cell = cellFactory.createCell("", 1, 1);
        int firstValueToAdd = 3;
        int secondValueToAdd = 14;
        CellContent cellContent1 = new ValueContent(firstValueToAdd);
        CellContent cellContent2 = new ValueContent(secondValueToAdd);
        cell.setContent(cellContent1);
        cell.setContent(cellContent2);
        Assert.assertEquals(cell.getContents().size(), 2);
        Assert.assertEquals(cell.getContents().get(0).getNumberValue(), firstValueToAdd);
        Assert.assertEquals(cell.getContents().get(1).getNumberValue(), secondValueToAdd);

    }
}
