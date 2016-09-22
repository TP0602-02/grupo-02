package ar.fiuba.tdd.template;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
            ArrayList<CellContent<?>> valuesRecovered = board.getValues(row, col);
            //System.out.println("Value row " + row + " col " + col + " is " + );
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

}
