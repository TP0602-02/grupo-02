package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.ValidNumberRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by alazraqui on 16/10/2016.
 */
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
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), 0));
    }

    @Test
    public void addValidNumber_ReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), 3));
    }

    @Test
    public void addHighNumber_ReturnFalse() {
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), 5));
    }
}
