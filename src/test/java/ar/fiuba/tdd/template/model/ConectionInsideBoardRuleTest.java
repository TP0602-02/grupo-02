package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.Play;
import ar.fiuba.tdd.template.rules.ConectionInsideBoardRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConectionInsideBoardRuleTest {
    private static final String LEFT = "1";
    private static final String RIGHT = "2";
    private static final String UP = "3";
    private static final String DOWN = "4";
    private Board board;
    private ConectionInsideBoardRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, "");
        this.rule = new ConectionInsideBoardRule();
    }

    @Test
    public void insertInvalidConnectionToLeft_ReturnFalse() {
        Play play = new Play(this.board.getCell(new Coordinate(2, 0)), LEFT);
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void insertInvalidConnectionToRight_ReturnFalse() {
        Play play = new Play(this.board.getCell(new Coordinate(1, 3)), RIGHT);
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void insertInvalidConnectionDown_ReturnFalse() {
        Play play = new Play(this.board.getCell(new Coordinate(3, 2)), DOWN);
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void insertInvalidConnectionUp_ReturnFalse() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 2)), UP);
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void insertValidConnectionInMiddle_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(1, 1)), DOWN);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void insertValidConnectionInBoard_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 1)), RIGHT);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void insertValidConnectionInBoardLine_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 1)), DOWN);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }
}
