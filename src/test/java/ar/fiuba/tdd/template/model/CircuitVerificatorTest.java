package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithoutBorders;
import ar.fiuba.tdd.template.entity.Coordinate;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CircuitVerificatorTest {

    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;

    @Test
    public void createCircuitVerificatorWithCountersInZero() {
        CircuitVerificatorWithoutBorders verificator = new CircuitVerificatorWithoutBorders();
        Assert.assertTrue(verificator.getAmountOfCellsInTheCircuit() == 0);
    }

    @Test
    public void detectCircuit_ReturnTrue() {
        //TODO Cambiar por movimientos.
        Board board = new Board(4, 4, "");
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(board.getCell(new Coordinate(0, 0)));
        cells.add(board.getCell(new Coordinate(0, 1)));
        cells.add(board.getCell(new Coordinate(0, 2)));
        cells.add(board.getCell(new Coordinate(1, 2)));
        cells.add(board.getCell(new Coordinate(2, 2)));
        cells.add(board.getCell(new Coordinate(2, 1)));
        cells.add(board.getCell(new Coordinate(2, 0)));
        cells.add(board.getCell(new Coordinate(1, 0)));
        cells.add(board.getCell(new Coordinate(1, 1)));
        Region region = new Region(cells);
        board.addRegion(region);
        setMovements(board);
        CircuitVerificatorWithoutBorders verificator = new CircuitVerificatorWithoutBorders();
        Assert.assertTrue(verificator.isCircuitClosed(board));
    }

    private void setMovements(Board board) {
        board.getCell(new Coordinate(0, 0)).addContent(new ValueContent(RIGHT));
        board.getCell(new Coordinate(0, 1)).addContent(new ValueContent(LEFT));
        board.getCell(new Coordinate(0, 1)).addContent(new ValueContent(RIGHT));
        board.getCell(new Coordinate(0, 2)).addContent(new ValueContent(LEFT));
        board.getCell(new Coordinate(0, 2)).addContent(new ValueContent(DOWN));
        board.getCell(new Coordinate(1, 2)).addContent(new ValueContent(UP));
        board.getCell(new Coordinate(1, 2)).addContent(new ValueContent(DOWN));
        board.getCell(new Coordinate(2, 2)).addContent(new ValueContent(UP));
        board.getCell(new Coordinate(2, 2)).addContent(new ValueContent(LEFT));
        board.getCell(new Coordinate(2, 1)).addContent(new ValueContent(RIGHT));
        board.getCell(new Coordinate(2, 1)).addContent(new ValueContent(LEFT));
        board.getCell(new Coordinate(2, 0)).addContent(new ValueContent(RIGHT));
        board.getCell(new Coordinate(2, 0)).addContent(new ValueContent(UP));
        board.getCell(new Coordinate(1, 0)).addContent(new ValueContent(DOWN));
        board.getCell(new Coordinate(1, 0)).addContent(new ValueContent(UP));
        board.getCell(new Coordinate(0, 0)).addContent(new ValueContent(DOWN));
    }

    @Test
    public void detectCircuit_ReturnFalse() {
        Board board = new Board(4, 4, "");
        board.getCell(new Coordinate(0, 0)).addContent(new ValueContent(RIGHT));
        board.getCell(new Coordinate(0, 1)).addContent(new ValueContent(LEFT));
        board.getCell(new Coordinate(0, 1)).addContent(new ValueContent(RIGHT));
        board.getCell(new Coordinate(0, 2)).addContent(new ValueContent(LEFT));
        board.getCell(new Coordinate(0, 2)).addContent(new ValueContent(DOWN));
        board.getCell(new Coordinate(1, 2)).addContent(new ValueContent(UP));
        board.getCell(new Coordinate(1, 2)).addContent(new ValueContent(DOWN));
        board.getCell(new Coordinate(2, 2)).addContent(new ValueContent(UP));
        board.getCell(new Coordinate(2, 2)).addContent(new ValueContent(LEFT));
        board.getCell(new Coordinate(2, 1)).addContent(new ValueContent(RIGHT));
        board.getCell(new Coordinate(2, 1)).addContent(new ValueContent(LEFT));
        board.getCell(new Coordinate(2, 0)).addContent(new ValueContent(RIGHT));
        board.getCell(new Coordinate(2, 0)).addContent(new ValueContent(UP));
        board.getCell(new Coordinate(1, 0)).addContent(new ValueContent(DOWN));
        CircuitVerificatorWithoutBorders verificator = new CircuitVerificatorWithoutBorders();
        Assert.assertFalse(verificator.isCircuitClosed(board));
    }

    @Test
    public void detectCircuitWithASeparatedCell_ReturnFalse() {
        Board board = new Board(4, 4, "");
        board.getCell(new Coordinate(0, 0)).addContent(new ValueContent(RIGHT));
        board.getCell(new Coordinate(0, 1)).addContent(new ValueContent(LEFT));

        board.getCell(new Coordinate(0, 2)).addContent(new ValueContent(RIGHT));
        board.getCell(new Coordinate(0, 3)).addContent(new ValueContent(LEFT));

        board.getCell(new Coordinate(0, 3)).addContent(new ValueContent(DOWN));
        board.getCell(new Coordinate(1, 3)).addContent(new ValueContent(UP));
        board.getCell(new Coordinate(1, 3)).addContent(new ValueContent(DOWN));
        board.getCell(new Coordinate(2, 3)).addContent(new ValueContent(UP));
        board.getCell(new Coordinate(2, 3)).addContent(new ValueContent(LEFT));
        board.getCell(new Coordinate(2, 2)).addContent(new ValueContent(RIGHT));

        board.getCell(new Coordinate(2, 2)).addContent(new ValueContent(UP));
        board.getCell(new Coordinate(1, 2)).addContent(new ValueContent(DOWN));
        board.getCell(new Coordinate(1, 2)).addContent(new ValueContent(UP));
        board.getCell(new Coordinate(0, 2)).addContent(new ValueContent(DOWN));
        CircuitVerificatorWithoutBorders verificator = new CircuitVerificatorWithoutBorders();
        Assert.assertFalse(verificator.isCircuitClosed(board));
    }
}
