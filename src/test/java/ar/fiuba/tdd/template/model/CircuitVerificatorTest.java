package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.CircuitVerificator;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 09/10/16.
 */
public class CircuitVerificatorTest {

    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;


    @Test
    public void createCircuitVerificatorWithCountersInZero() {
        CircuitVerificator verificator = new CircuitVerificator();
        Assert.assertTrue(verificator.getAmountOfCellsInTheCircuit() == 0);
    }

    @Test
    public void detectCircuit_ReturnTrue() {
        //TODO Cambiar por movimientos.
        Board board = new Board(4, 4, "");
        ArrayList<Cell> cells = new ArrayList<Cell>();
        cells.add(board.getCell(0, 0));
        cells.add(board.getCell(0, 1));
        cells.add(board.getCell(0, 2));
        cells.add(board.getCell(1, 2));
        cells.add(board.getCell(2, 2));
        cells.add(board.getCell(2, 1));
        cells.add(board.getCell(2, 0));
        cells.add(board.getCell(1, 0));
        cells.add(board.getCell(1, 1));
        Region region = new Region(cells);
        board.addRegion(region);
        setMovements(board);
        CircuitVerificator verificator = new CircuitVerificator();
        Assert.assertTrue(verificator.isCircuitClosed(board));
    }

    private void setMovements(Board board) {
        board.getCell(0, 0).setContent(new ValueContent(RIGHT));
        board.getCell(0, 1).setContent(new ValueContent(LEFT));
        board.getCell(0, 1).setContent(new ValueContent(RIGHT));
        board.getCell(0, 2).setContent(new ValueContent(LEFT));
        board.getCell(0, 2).setContent(new ValueContent(DOWN));
        board.getCell(1, 2).setContent(new ValueContent(UP));
        board.getCell(1, 2).setContent(new ValueContent(DOWN));
        board.getCell(2, 2).setContent(new ValueContent(UP));
        board.getCell(2, 2).setContent(new ValueContent(LEFT));
        board.getCell(2, 1).setContent(new ValueContent(RIGHT));
        board.getCell(2, 1).setContent(new ValueContent(LEFT));
        board.getCell(2, 0).setContent(new ValueContent(RIGHT));
        board.getCell(2, 0).setContent(new ValueContent(UP));
        board.getCell(1, 0).setContent(new ValueContent(DOWN));
        board.getCell(1, 0).setContent(new ValueContent(UP));
        board.getCell(0, 0).setContent(new ValueContent(DOWN));
    }

    @Test
    public void detectCircuit_ReturnFalse() {
        Board board = new Board(4, 4, "");
        board.getCell(0, 0).setContent(new ValueContent(RIGHT));
        board.getCell(0, 1).setContent(new ValueContent(LEFT));
        board.getCell(0, 1).setContent(new ValueContent(RIGHT));
        board.getCell(0, 2).setContent(new ValueContent(LEFT));
        board.getCell(0, 2).setContent(new ValueContent(DOWN));
        board.getCell(1, 2).setContent(new ValueContent(UP));
        board.getCell(1, 2).setContent(new ValueContent(DOWN));
        board.getCell(2, 2).setContent(new ValueContent(UP));
        board.getCell(2, 2).setContent(new ValueContent(LEFT));
        board.getCell(2, 1).setContent(new ValueContent(RIGHT));
        board.getCell(2, 1).setContent(new ValueContent(LEFT));
        board.getCell(2, 0).setContent(new ValueContent(RIGHT));
        board.getCell(2, 0).setContent(new ValueContent(UP));
        board.getCell(1, 0).setContent(new ValueContent(DOWN));
        CircuitVerificator verificator = new CircuitVerificator();
        Assert.assertFalse(verificator.isCircuitClosed(board));
    }

    @Test
    public void detectCircuitWithASeparatedCell_ReturnFalse() {
        Board board = new Board(4, 4, "");
        board.getCell(0, 0).setContent(new ValueContent(RIGHT));
        board.getCell(0, 1).setContent(new ValueContent(LEFT));

        board.getCell(0, 2).setContent(new ValueContent(RIGHT));
        board.getCell(0, 3).setContent(new ValueContent(LEFT));

        board.getCell(0, 3).setContent(new ValueContent(DOWN));
        board.getCell(1, 3).setContent(new ValueContent(UP));
        board.getCell(1, 3).setContent(new ValueContent(DOWN));
        board.getCell(2, 3).setContent(new ValueContent(UP));
        board.getCell(2, 3).setContent(new ValueContent(LEFT));
        board.getCell(2, 2).setContent(new ValueContent(RIGHT));

        board.getCell(2, 2).setContent(new ValueContent(UP));
        board.getCell(1, 2).setContent(new ValueContent(DOWN));
        board.getCell(1, 2).setContent(new ValueContent(UP));
        board.getCell(0, 2).setContent(new ValueContent(DOWN));
        CircuitVerificator verificator = new CircuitVerificator();
        Assert.assertFalse(verificator.isCircuitClosed(board));
    }

    @Test
    public void insertValidMovement_returnTrue() {
        Board board = new Board(4, 4, "");
        CircuitVerificator verificator = new CircuitVerificator();
        Assert.assertTrue(verificator.validateDirection(board, board.getCell(2, 2), UP));
    }

    @Test
    public void insertRightValidMovement_returnFalse() {
        Board board = new Board(4, 4, "");
        CircuitVerificator verificator = new CircuitVerificator();
        Assert.assertTrue(!verificator.validateDirection(board, board.getCell(2, 3), RIGHT));
    }

    @Test
    public void insertLeftValidMovement_returnFalse() {
        Board board = new Board(4, 4, "");
        CircuitVerificator verificator = new CircuitVerificator();
        Assert.assertTrue(!verificator.validateDirection(board, board.getCell(2, 0), LEFT));
    }

    @Test
    public void insertDownValidMovement_returnFalse() {
        Board board = new Board(4, 4, "");
        CircuitVerificator verificator = new CircuitVerificator();
        Assert.assertTrue(!verificator.validateDirection(board, board.getCell(3, 2), DOWN));
    }

    @Test
    public void insertUpValidMovement_returnFalse() {
        Board board = new Board(4, 4, "");
        CircuitVerificator verificator = new CircuitVerificator();
        Assert.assertTrue(!verificator.validateDirection(board, board.getCell(0, 3), UP));
    }

}
