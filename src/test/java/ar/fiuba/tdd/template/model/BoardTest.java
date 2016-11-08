package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.Parser;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.*;
import ar.fiuba.tdd.template.entity.Coordinate;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void createEmptyBoard() {
        int height = 3;
        int width = 5;
        Board board = new Board(height, width, CellFactory.CELL_SINGLE_VALUE);
        int row = 2;
        int col = 3;
        Assert.assertTrue(board.getCell(new Coordinate(row, col)).isEmpty());
    }

    @Test
    public void emptyBoardIsFull_ReturnFalse() {
        Board board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        Assert.assertFalse(board.isFull());
    }

    @Test
    public void boardWithDifferentValuesIsFull_ReturnFalse() {
        Board board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        board.setValue(new Coordinate(1, 1), new BlackContent());
        board.setValue(new Coordinate(1, 2), new ClueContent(1));
        board.setValue(new Coordinate(3, 3), new ValueContent(1));
        Assert.assertFalse(board.isFull());
    }

    @Test
    public void boardWithDiferentValuesIsFull_ReturnTrue() {
        Board board = new Board(2, 2, CellFactory.CELL_SINGLE_VALUE);
        board.setValue(new Coordinate(1, 1), new BlackContent());
        board.setValue(new Coordinate(0, 0), new ValueContent(1));
        board.setValue(new Coordinate(0, 1), new ClueContent(1));
        board.setValue(new Coordinate(1, 0), new ValueContent(1));
        Assert.assertTrue(board.isFull());
    }


    @Test
    public void getCellRegionReturnEmpty() {
        Board board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        ArrayList<Cell> firstCells = new ArrayList<>();
        firstCells.add(board.getCell(new Coordinate(1, 1)));
        firstCells.add(board.getCell(new Coordinate(0, 0)));
        firstCells.add(board.getCell(new Coordinate(0, 1)));
        ArrayList<Cell> secondCells = new ArrayList<>();
        secondCells.add(board.getCell(new Coordinate(2, 1)));
        secondCells.add(board.getCell(new Coordinate(2, 0)));
        secondCells.add(board.getCell(new Coordinate(2, 1)));
        board.addRegion(new Region(firstCells));
        board.addRegion(new Region(secondCells));
        assertTrue(board.getCellRegions(board.getCell(new Coordinate(2, 2))).isEmpty());
    }

    @Test
    public void getCellRegionReturnFirstRegion() {
        Board board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        ArrayList<Cell> firstCells = new ArrayList<>();
        firstCells.add(board.getCell(new Coordinate(1, 1)));
        firstCells.add(board.getCell(new Coordinate(0, 0)));
        firstCells.add(board.getCell(new Coordinate(0, 1)));
        ArrayList<Cell> secondCells = new ArrayList<>();
        secondCells.add(board.getCell(new Coordinate(2, 1)));
        secondCells.add(board.getCell(new Coordinate(2, 0)));
        secondCells.add(board.getCell(new Coordinate(2, 1)));
        Region firstRegion = new Region(firstCells);
        board.addRegion(firstRegion);
        board.addRegion(new Region(secondCells));
        assertTrue(board.getCellRegions(board.getCell(new Coordinate(0, 0))).contains(firstRegion));
    }

    @Test
    public void getInvalidIndexReturnsNull() {
        Board board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        Assert.assertNull(board.getCell(new Coordinate(1, 5)));
    }

    @Test
    public void getAdyacentsCellInExtremeReturnsTwoCells() {
        Board board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        Assert.assertTrue(board.getAdyacentCells(board.getCell(new Coordinate(3, 3))).size() == 2);
    }

    @Test
    public void getAdyacentsCellInMiddleReturnsFourCells() {
        Board board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        Assert.assertTrue(board.getAdyacentCells(board.getCell(new Coordinate(1, 1))).size() == 4);
    }

    @Test
    public void getAdyacentsCellInBoardReturnsThreeCells() {
        Board board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        Assert.assertTrue(board.getAdyacentCells(board.getCell(new Coordinate(1, 3))).size() == 3);
    }

    @Test
    public void getCellRegionReturnTwoRegions() {
        Board board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        ArrayList<Cell> firstCells = new ArrayList<>();
        firstCells.add(board.getCell(new Coordinate(2, 1)));
        firstCells.add(board.getCell(new Coordinate(0, 0)));
        firstCells.add(board.getCell(new Coordinate(0, 1)));
        ArrayList<Cell> secondCells = new ArrayList<>();
        secondCells.add(board.getCell(new Coordinate(2, 1)));
        secondCells.add(board.getCell(new Coordinate(2, 0)));
        secondCells.add(board.getCell(new Coordinate(2, 1)));
        Region firstRegion = new Region(firstCells);
        board.addRegion(firstRegion);
        board.addRegion(new Region(secondCells));
        assertTrue(board.getCellRegions(board.getCell(new Coordinate(2, 1))).size() == 2);
    }

    @Test
    public void setValueOnBoard() {
        int height = 3;
        int width = 5;
        Board board = new Board(height, width, CellFactory.CELL_SINGLE_VALUE);
        int row = 1;
        int column = 1;
        ValueContent firstValue = new ValueContent(2);
        board.setValue(new Coordinate(row, column), firstValue);
        ArrayList<CellContent> valuesRecovered = board.getContents(new Coordinate(row, column));
        Assert.assertTrue(valuesRecovered.contains(firstValue));
    }

    @Test
    public void setMultipleContentsOnBoard() {
        int height = 3;
        int width = 5;
        Board board = new Board(height, width, CellFactory.CELL_SINGLE_VALUE);
        int row = 1;
        int column = 1;
        ValueContent firstContent = new ValueContent(2);
        ClueContent secondContent = new ClueContent(3);
        ArrayList<CellContent> values = new ArrayList<>();
        values.add(firstContent);
        values.add(secondContent);

        board.setValues(new Coordinate(row, column), values);
        ArrayList<CellContent> valuesRecovered = board.getContents(new Coordinate(row, column));
        //Remueve el primer valor, y agregar el segundo. Simulacion de agregar un valor a una celda y luego
        //agregar otro a la misma celda.
        Assert.assertTrue(!(valuesRecovered.contains(firstContent) && valuesRecovered.contains(secondContent)));
    }

    @Test
    public void getRows() {
        int height = 5;
        int width = 5;
        Board board = new Board(height, width, CellFactory.CELL_SINGLE_VALUE);
        // Creates a list of numbers
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                board.setValue(new Coordinate(row, column), new ValueContent(row));
            }
        }

        ArrayList<Cell> rowCells = board.getRow(board.getCell(new Coordinate(1, 0)));
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Cell cell : rowCells) {
            ArrayList<CellContent> cellC = board.getContents(new Coordinate(cell.getRow(), cell.getColumn()));
            numbers.add(cellC.get(0).getNumberValue());
        }
        // row of cell wanted is 1
        assertEquals(numbers, (Arrays.asList(1, 1, 1, 1, 1)));
    }

    @Test
    public void getColumns() {
        int height = 5;
        int width = 5;

        Board board = new Board(height, width, CellFactory.CELL_SINGLE_VALUE);
        // Creates a list of numbers
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                board.setValue(new Coordinate(row, column), new ValueContent(column));
            }
        }

        ArrayList<Cell> firstColumn = board.getColumn(board.getCell(new Coordinate(1, 0)));
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Cell cell : firstColumn) {
            ArrayList<CellContent> cellC = board.getContents(new Coordinate(cell.getRow(), cell.getColumn()));
            numbers.add(cellC.get(0).getNumberValue());
        }
        // column of cell wanted is 0
        assertEquals(numbers, (Arrays.asList(0, 0, 0, 0, 0)));
    }

    @Test
    public void decodeJson() {
        Parser parser = new Parser();
        String file = "InshiNoHeya.json";
        parser.decodeJson(file, null);
        int width = parser.getWidth();
        int height = parser.getHeight();
        Board board = new Board(height, width, CellFactory.CELL_SINGLE_VALUE);
        assertEquals(board.getHeight(), height);
        assertEquals(board.getWidth(), width);
    }

}
