package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithBorders;
import ar.fiuba.tdd.template.entity.Coordinate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CircuitVerificatorWithBordersTest {
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;
    private CircuitVerificatorWithBorders circuit;
    private Board board;

    @Before
    public void setUp() {
        this.board = new Board(6, 6, "");
        this.circuit = new CircuitVerificatorWithBorders();
    }

    @Test
    public void firstCellInBoard_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 0), new ValueContent(LEFT));
        Assert.assertFalse(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void firstCellInMiddleBoard_ReturnFalse() {
        this.board.setValue(new Coordinate(3, 4), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(3, 3), new ValueContent(RIGHT));
        Assert.assertFalse(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void squareInBoard_ReturnTrue() {
        this.setVertical();
        this.setHorizontal();
        Assert.assertTrue(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void setVerticalLinesInBoard_ReturnFalse() {
        this.setVertical();
        Assert.assertFalse(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void setHorizontalLinesInBoard_ReturnFalse() {
        this.setHorizontal();
        Assert.assertFalse(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void setSquareWithLineOutsideCircuit_ReturnFalse() {
        this.setHorizontal();
        this.setVertical();
        this.board.setValue(new Coordinate(5, 5), new ValueContent(RIGHT));
        Assert.assertTrue(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void setSquareWithLineBeforeOutsideCircuit_ReturnFalse() {
        this.setHorizontal();
        this.setVertical();
        this.board.setValue(new Coordinate(0, 0), new ValueContent(RIGHT));
        Assert.assertFalse(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void setTwoSquares_ReturnFalse() {
        this.board.setValue(new Coordinate(2, 0), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(3, 0), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(2, 0), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(3, 0), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 1), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(3, 1), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(2, 0), new ValueContent(UP));
        this.board.setValue(new Coordinate(3, 0), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(4, 0), new ValueContent(UP));
        this.board.setValue(new Coordinate(4, 0), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(4, 0), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(5, 0), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(5, 0), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(5, 0), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(4, 1), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(5, 1), new ValueContent(LEFT));
        Assert.assertTrue(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void setSquareWithOneVerticalHoleInBoard_ReturnFalse() {
        this.setHorizontal();
        Assert.assertFalse(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void setSquarewithLineInside_ReturnFalse() {
        setVertical();
        setHorizontal();
        this.board.setValue(new Coordinate(2, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(LEFT));
        Assert.assertTrue(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void setCloseCircuitInOneBorder_ReturnTrue() {
        this.board.setValue(new Coordinate(2, 0), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(3, 0), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(2, 0), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(3, 0), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 1), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(3, 1), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(2, 0), new ValueContent(UP));
        this.board.setValue(new Coordinate(3, 0), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(4, 0), new ValueContent(UP));
        Assert.assertTrue(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void setCloseCircuitInTwoBorders_ReturnTrue() {
        this.board.setValue(new Coordinate(4, 0), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(4, 0), new ValueContent(UP));
        this.board.setValue(new Coordinate(4, 0), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(3, 0), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(5, 0), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(5, 0), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(5, 0), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(4, 1), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(5, 1), new ValueContent(LEFT));
        Assert.assertTrue(this.circuit.isCircuitClosed(this.board));
    }

    private void setVertical() {
        this.board.setValue(new Coordinate(1, 1), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 1), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(3, 1), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(2, 2), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(3, 2), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 4), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 4), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(3, 4), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 5), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(2, 5), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(3, 5), new ValueContent(LEFT));
    }

    private void setVerticalWithHole() {
        this.board.setValue(new Coordinate(1, 1), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 1), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(2, 2), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 4), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 4), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(3, 4), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 5), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(2, 5), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(3, 5), new ValueContent(LEFT));
    }

    private void setHorizontal() {
        this.board.setValue(new Coordinate(0, 2), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(0, 3), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(0, 4), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(UP));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(UP));
        this.board.setValue(new Coordinate(1, 4), new ValueContent(UP));
        this.board.setValue(new Coordinate(3, 2), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(3, 3), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(3, 4), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(4, 2), new ValueContent(UP));
        this.board.setValue(new Coordinate(4, 3), new ValueContent(UP));
        this.board.setValue(new Coordinate(4, 4), new ValueContent(UP));
    }
}
