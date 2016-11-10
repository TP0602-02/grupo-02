package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.Play;
import ar.fiuba.tdd.template.rules.CellHasValidConectionsRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CellHasValidConectionsRuleTest {
    private static final String LEFT = "1";
    private static final String RIGHT = "2";
    private static final String UP = "3";
    private static final String DOWN = "4";
    private Board board;
    private CellHasValidConectionsRule rule;

    @Before
    public void setUp() {
        this.board = new Board(9, 9, "");
        this.rule = new CellHasValidConectionsRule();
        this.board.setValue(new Coordinate(0, 0), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(0, 1), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(0, 1), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(1, 1), new ValueContent(UP));
    }

    @Test
    public void connectTwoCellsWithNoConnections_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(5, 5)), RIGHT);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void connectCellWithOneValuesWithCellWithOneValue_ReturnTrue() {
        this.board.setValue(new Coordinate(1, 0), new ValueContent(DOWN));
        Play play = new Play(this.board.getCell(new Coordinate(1, 1)), RIGHT);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void connectCellWithNoValuesWithCellWithOneValue_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(1, 2)), LEFT);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void connectCellWithNoValuesWithCellWithTwoValues_ReturnFalse() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 2)), LEFT);
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void connectCellWithTwoValuesWithCellWithNoValue_ReturnFalse() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 1)), RIGHT);
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void connectCellWithOneValuesWithCellWithTwoValues_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 2), new ValueContent(RIGHT));
        Play play = new Play(this.board.getCell(new Coordinate(0, 2)), LEFT);
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void connectCellWithTwoValuesWithCellWithTwoValues_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(0, 2), new ValueContent(DOWN));
        Play play = new Play(this.board.getCell(new Coordinate(0, 2)), LEFT);
        Assert.assertFalse(this.rule.validate(this.board, play));
    }
}
