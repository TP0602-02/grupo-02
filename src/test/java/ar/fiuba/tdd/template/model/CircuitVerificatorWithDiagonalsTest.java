package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithDiagonals;
import ar.fiuba.tdd.template.entity.Coordinate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by matiaskamien on 18/10/16.
 */
public class CircuitVerificatorWithDiagonalsTest {

    private static final int UPPER_LEFT_CORNER = 1;
    private static final int UPPER_RIGHT_CORNER = 2;
    private static final int LOWER_LEFT_CORNER = 3;
    private static final int LOWER_RIGHT_CORNER = 4;

    private CircuitVerificatorWithDiagonals circuit;
    private Board board;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, "");
        this.circuit = new CircuitVerificatorWithDiagonals();
    }

    @Test
    public void detectAClosedCircuit_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 0), new ValueContent(UPPER_RIGHT_CORNER));
        this.board.setValue(new Coordinate(0, 0), new ValueContent(LOWER_LEFT_CORNER));

        this.board.setValue(new Coordinate(0, 1), new ValueContent(UPPER_LEFT_CORNER));
        this.board.setValue(new Coordinate(0, 1), new ValueContent(LOWER_RIGHT_CORNER));

        this.board.setValue(new Coordinate(1, 0), new ValueContent(UPPER_LEFT_CORNER));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(LOWER_RIGHT_CORNER));

        this.board.setValue(new Coordinate(1, 1), new ValueContent(UPPER_RIGHT_CORNER));
        this.board.setValue(new Coordinate(1, 1), new ValueContent(LOWER_LEFT_CORNER));

        this.circuit.setCell(this.board.getCell(new Coordinate(0, 1)));
        Assert.assertTrue(this.circuit.isCircuitClosed(this.board));
    }

    @Test
    public void detectAClosedCircuit_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 0), new ValueContent(UPPER_RIGHT_CORNER));
        this.board.setValue(new Coordinate(0, 0), new ValueContent(LOWER_LEFT_CORNER));

        this.board.setValue(new Coordinate(0, 1), new ValueContent(UPPER_LEFT_CORNER));
        this.board.setValue(new Coordinate(0, 1), new ValueContent(LOWER_RIGHT_CORNER));

        this.board.setValue(new Coordinate(1, 0), new ValueContent(UPPER_LEFT_CORNER));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(LOWER_RIGHT_CORNER));

        this.circuit.setCell(this.board.getCell(new Coordinate(0, 1)));
        Assert.assertTrue(!this.circuit.isCircuitClosed(this.board));
    }
}
