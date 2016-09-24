package ar.fiuba.tdd.template;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MainTests {

    @Test
    public void createEmptyBoard() {
        int height = 3;
        int width = 5;
        Board<Integer> board = new Board<>(height, width); // TODO: should we only accept n x n boards?

        int row = 2;
        int col = 3;

        try {
            board.getValues(row, col);
            assert false;
        } catch (AssertionError e) {
            System.out.println("Value row " + row + " col " + col + " is empty");
            assert true;
        }
        
    }

    @Test
    public void setValueOnBoard() {
        int height = 3;
        int width = 5;
        Board<Integer> board = new Board<>(height, width);

        int row = 1;
        int column = 1;
        ValueCell newValue = new ValueCell<>(2);

        board.setValue(row, column, newValue);
        try {
            ArrayList<CellContent<?>> valuesRecovered = board.getValues(row, column);
            int index = valuesRecovered.indexOf(newValue);
            System.out.println("\nValue row " + row + " col " + column + " is " + valuesRecovered.get(index).getValue() + "\n");
            assertEquals(valuesRecovered.get(valuesRecovered.indexOf(newValue)), newValue);
        } catch (AssertionError e) {
            System.out.println("\nValue row " + row + " col " + column + " is empty\n");
            assert false;
        }
    }

    @Test
    public void getBlackCellValues() {
        BlackCell<String> emptyBlackCell = new BlackCell<>();

        assertEquals(emptyBlackCell.getClues().get(0),"Empty Value");

        // Creates a list of numbers
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

        BlackCell<Integer> nonEmptyBlackCell = new BlackCell<>(numbers);

        assertEquals(nonEmptyBlackCell.getClues(),numbers);

    }

    @Test
    public void setValueAfterBlackOnBoard() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        BlackCell<Integer> nonEmptyBlackCell = new BlackCell<>(numbers);

        int row = 0;
        int column = 0;
        int height = 3;
        int width = 5;
        Board<Integer> board = new Board<>(height, width);
        board.setValue(row, column, nonEmptyBlackCell);

        ValueCell newValue = new ValueCell<>(2);

        try {
            board.setValue(row, column, newValue);
            assert false;
        } catch (AssertionError e) {
            System.out.print("Value cannot be set here: there's a black already!");
            assert true;
        }
    }

    @Test
    public void getRows() {
        int height = 5;
        int width = 5;

        Board<Integer> numberBoard = new Board<>(height, width);

        // Creates a list of numbers
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                numberBoard.setValue(row, column, new ValueCell<>(row));
            }
        }

        ArrayList<Cell<Integer>> rowCells = numberBoard.getRow(new Cell<>(1,0));
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Cell<Integer> cell : rowCells) {
            ArrayList<CellContent<?>> cellC = numberBoard.getValues(cell.getRow(), cell.getColumn());
            numbers.add((Integer)cellC.get(0).getValue());
            System.out.print(cellC.get(0).getValue() + " ");
        }

        assertEquals(numbers,new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
    }

    @Test
    public void getColumns() {
        int height = 5;
        int width = 5;

        Board<Integer> numberBoard = new Board<>(height, width);

        // Creates a list of numbers
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                numberBoard.setValue(row, column, new ValueCell<>(column));
            }
        }

        ArrayList<Cell<Integer>> rowColumns = numberBoard.getColumn(new Cell<>(1,0));
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Cell<Integer> cell : rowColumns) {
            ArrayList<CellContent<?>> cellC = numberBoard.getValues(cell.getRow(), cell.getColumn());
            numbers.add((Integer)cellC.get(0).getValue());
            System.out.print(cellC.get(0).getValue() + " ");
        }

        assertEquals(numbers,new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
    }

    @Test
    public void decodeJson() {

        ArrayList<Object> boardElements = Parser.decodeJson();
        Integer width = (int)(long)boardElements.get(0);
        Integer height = (int)(long)boardElements.get(1);
        Board<Integer> board = new Board<>(height, width ); // TODO: should we only accept n x n boards?

        int row = 2;
        int col = 3;

        try {
            board.getValues(row, col);
            assert false;
        } catch (AssertionError e) {
            System.out.println("Value row " + row + " col " + col + " is empty");
            assert true;
        }

    }

}
