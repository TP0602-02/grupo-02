package ar.fiuba.tdd.template;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTests {

    @Test
    public void createEmptyBoard() {
        int height = 3;
        int width = 5;
        Board<Integer> board = new Board<>(height, width); // should we only accept n x n boards?

        boolean catchesException = false;

        for (int i =  0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                try {
                    System.out.println("Value row " + i + " col " + j + " is " + board.getValue(i, j));
                } catch (AssertionError e) {
                    System.out.println("Value row " + i + " col " + j + " is empty");
                    catchesException = true;
                }
            }
        }

        if (catchesException) {
            assert true;
        } else {
            assert false;
        }
    }

    @Test
    public void setValueOnBoard() {
        int height = 3;
        int width = 5;
        Board<Integer> board = new Board<>(height, width);

        int row = 1;
        int column = 1;
        int newValue = 2;

        board.setValue(row, column, newValue);
        try {
            int valueRecovered = board.getValue(row, column);
            System.out.println("\nValue row " + row + " col " + column + " is " + valueRecovered + "\n");
            assertEquals(valueRecovered, newValue);
        } catch (AssertionError e) {
            System.out.println("\nValue row " + row + " col " + column + " is empty\n");
            assert false;
        }
    }

}
