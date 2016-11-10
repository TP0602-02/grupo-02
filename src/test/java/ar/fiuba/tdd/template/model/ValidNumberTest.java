package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.Play;
import ar.fiuba.tdd.template.rules.ValidNumberRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidNumberTest {
    private Board board;
    private ValidNumberRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, "");
        this.rule = new ValidNumberRule();
    }

    @Test
    public void addZero_ReturnFalse() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 0)), "0");
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void addValidNumber_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 0)), "3");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addHighNumber_ReturnFalse() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 0)), "5");
        Assert.assertFalse(this.rule.validate(this.board, play));
    }
}
