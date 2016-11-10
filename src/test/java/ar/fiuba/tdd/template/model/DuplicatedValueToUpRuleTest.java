package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.Play;
import ar.fiuba.tdd.template.rules.DuplicatedValueUpRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DuplicatedValueToUpRuleTest {
    private Board board;
    private DuplicatedValueUpRule rule;

    @Before
    public void setUp() {
        this.board = new Board(5, 5, "");
        this.rule = new DuplicatedValueUpRule();
    }

    @Test
    public void insertValueInEmptyBoardInBorder_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 3)), "2");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void insertValueInEmptyBoard_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(3, 1)), "2");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void insertValuewithSameValueOutsideRange_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 4), new ValueContent(2));
        Play play = new Play(this.board.getCell(new Coordinate(3, 4)), "2");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void insertValuewithSameValueInsideRange_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 1), new ValueContent(2));
        Play play = new Play(this.board.getCell(new Coordinate(2, 1)), "2");
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void insertValuewithOtherValueInsideRange_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 2), new ValueContent(1));
        Play play = new Play(this.board.getCell(new Coordinate(2, 2)), "2");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void insertValuewithMultipleValuesInsideRange_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 3), new ValueContent(2));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(1));
        Play play = new Play(this.board.getCell(new Coordinate(2, 3)), "2");
        Assert.assertFalse(this.rule.validate(this.board, play));
    }
}
