package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.Parser;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.board.cell.CellContent;
import ar.fiuba.tdd.template.board.cell.ClueContent;
import ar.fiuba.tdd.template.board.cell.ValueContent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void createEmptyBoard() {
        int height = 3;
        int width = 5;
        Board<Integer> board = new Board<>(height, width); // TODO: should we only accept n x n boards?

        int row = 2;
        int col = 3;

        if (board.getCell(row, col).isEmpty()) {
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
        ValueContent firstValue = new ValueContent<>(2);

        board.setValue(row, column, firstValue);
        ArrayList<CellContent<?>> valuesRecovered = board.getContents(row, column);

        if (valuesRecovered.contains(firstValue)) {
            assert true;
        } else {
            assert false;
        }
    }

    @Test
    public void setMultipleContentsOnBoard() {
        int height = 3;
        int width = 5;
        Board<Integer> board = new Board<>(height, width);

        int row = 1;
        int column = 1;
        ValueContent firstContent = new ValueContent<>(2);
        ClueContent secondContent = new ClueContent<>(3);
        board.setValue(row, column, firstContent);
        board.setValue(row, column, secondContent);
        ArrayList<CellContent<?>> valuesRecovered = board.getContents(row, column);

        if (valuesRecovered.contains(firstContent) && valuesRecovered.contains(secondContent)) {
            assert true;
        } else {
            assert false;
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
                numberBoard.setValue(row, column, new ValueContent<>(row));
            }
        }

        ArrayList<Cell<Integer>> rowCells = numberBoard.getRow(new Cell<>(1,0));
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Cell<Integer> cell : rowCells) {
            ArrayList<CellContent<?>> cellC = numberBoard.getContents(cell.getRow(), cell.getColumn());
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
                numberBoard.setValue(row, column, new ValueContent<>(column));
            }
        }

        ArrayList<Cell<Integer>> rowColumns = numberBoard.getColumn(new Cell<>(1,0));
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Cell<Integer> cell : rowColumns) {
            ArrayList<CellContent<?>> cellC = numberBoard.getContents(cell.getRow(), cell.getColumn());
            numbers.add((Integer)cellC.get(0).getValue());
            System.out.print(cellC.get(0).getValue() + " ");
        }

        assertEquals(numbers,new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
    }

    @Test
    public void decodeJson() {
        Parser parser = new Parser();
        parser.decodeJson();
        int width = parser.getWidth();
        int height = parser.getHeight();
        Board<Integer> board = new Board<>(height, width);

        assertEquals(board.getHeight(), height);
        assertEquals(board.getWidth(), width);

        ArrayList<Cell> clues = parser.getBoardElements();
        System.out.print("The number of clues is : " + clues.size() + "\n");
        System.out.print("The number of contents\n");
        for (int i = 0; i < clues.size(); i++) {
            ArrayList<CellContent> content = clues.get(i).getContents();
            System.out.print(" in clue " + clues.get(i).getRow() + " " + clues.get(i).getColumn()
                    + " is " + content.size() + "\n");
        }

    }

}
