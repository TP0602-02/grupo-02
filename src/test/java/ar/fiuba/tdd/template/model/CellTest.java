package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.cell.model.*;
import ar.fiuba.tdd.template.entity.Coordinate;
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
        Assert.assertTrue(cellFactory.createCell(CellFactory.CELL_SINGLE_VALUE, (new Coordinate(0, 0))) instanceof CellSingleValue);
    }

    @Test
    public void cellMultipleValueIsTheDefaultCellToCreate() {
        Assert.assertTrue(cellFactory.createCell("djksnld", (new Coordinate(0, 0))) instanceof CellMultipleValue);
        Assert.assertTrue(cellFactory.createCell("", (new Coordinate(0, 0))) instanceof CellMultipleValue);
    }


    @Test
    public void cellSingleValueAllowOnlyOneValueToAdd() {
        Cell cell = cellFactory.createCell(CellFactory.CELL_SINGLE_VALUE, (new Coordinate(0, 0)));
        int firstValueToAdd = 3;
        int secondValueToAdd = 14;
        CellContent cellContent1 = new ValueContent(firstValueToAdd);
        CellContent cellContent2 = new ValueContent(secondValueToAdd);
        cell.addContent(cellContent1);
        cell.addContent(cellContent2);
        Assert.assertEquals(cell.getContents().size(), 1);
        Assert.assertEquals(cell.getContents().get(0).getNumberValue(), secondValueToAdd);

    }


    @Test
    public void cellMultipleValueAllowMultipleValueToAdd() {
        Cell cell = cellFactory.createCell("", (new Coordinate(0, 0)));
        int firstValueToAdd = 3;
        int secondValueToAdd = 14;
        CellContent cellContent1 = new ValueContent(firstValueToAdd);
        CellContent cellContent2 = new ValueContent(secondValueToAdd);
        cell.addContent(cellContent1);
        cell.addContent(cellContent2);
        Assert.assertEquals(cell.getContents().size(), 2);
        Assert.assertEquals(cell.getContents().get(0).getNumberValue(), firstValueToAdd);
        Assert.assertEquals(cell.getContents().get(1).getNumberValue(), secondValueToAdd);

    }

    @Test
    public void deleteCellContentInASingeCellValue() {
        Cell cell = cellFactory.createCell(CellFactory.CELL_SINGLE_VALUE, (new Coordinate(0, 0)));
        String value = "3";
        cell.addContent(new ValueContent(value));
        cell.removeContentWithValue(value);
        Assert.assertTrue(cell.getContents().isEmpty());
    }

    @Test
    public void deleteCellContentInAMultipleCellValue() {
        Cell cell = cellFactory.createCell("", (new Coordinate(0, 0)));
        String value1 = "3";
        String value2 = "4";
        cell.addContent(new ValueContent(value1));
        cell.addContent(new ValueContent(value2));
        cell.removeContentWithValue(value2);
        Assert.assertEquals(cell.getContents().size(), 1);
        Assert.assertEquals(cell.getContents().get(0).getValue(), value1);
    }
}
