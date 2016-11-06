package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.ConectionInsideBoardRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by alazraqui on 13/10/2016.
 */
public class ConectionInsideBoardRuleTest {
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;
    private Board board;
    private ConectionInsideBoardRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, "");
        this.rule = new ConectionInsideBoardRule();
    }

    @Test
    public void insertInvalidConectionToLeft_returnFalse() {
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(2, 0)), LEFT));
    }

    @Test
    public void insertInvalidConectionToRight_returnFalse() {
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(1, 3)), RIGHT));
    }

    @Test
    public void insertInvalidConectionDown_returnFalse() {
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(3, 2)), DOWN));
    }

    @Test
    public void insertInvalidConectionUp_returnFalse() {
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 2)), UP));
    }

    @Test
    public void insertValidConectionInMiddle_ReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(1, 1)), DOWN));
    }

    @Test
    public void insertValidConectionInBoard_ReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 1)), RIGHT));
    }

    @Test
    public void insertValidConectionInBoardLine_ReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 1)), DOWN));
    }


}
