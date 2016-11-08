package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.DuplicatedValueToRightRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DuplicatedValueToRightRuleTest {
    private Board board;
    private DuplicatedValueToRightRule rule;

    @Before
    public void setUp() {
        this.board = new Board(5, 5, "");
        this.rule = new DuplicatedValueToRightRule();
    }

    @Test
    public void insertValueInEmptyBoardInBorder_ReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 3)), 2));
    }

    @Test
    public void insertValueInEmptyBoard_ReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 1)), 2));
    }

    @Test
    public void insertValuewithSameValueOutsideRange_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 4), new ValueContent(2));
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 1)), 2));
    }

    @Test
    public void insertValuewithSameValueInsideRange_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 3), new ValueContent(2));
        Assert.assertFalse(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 1)), 2));
    }

    @Test
    public void insertValuewithOtherValueInsideRange_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 4), new ValueContent(1));
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 2)), 2));
    }

    @Test
    public void insertValuewithMultipleValuesInsideRange_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 3), new ValueContent(2));
        this.board.setValue(new Coordinate(0, 2), new ValueContent(1));
        Assert.assertFalse(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 1)), 2));
    }

}
