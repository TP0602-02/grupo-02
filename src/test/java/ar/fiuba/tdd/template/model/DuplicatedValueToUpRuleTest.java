package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.DuplicatedValueUpRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by alazraqui on 02/11/2016.
 */
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
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 3)), 2));
    }

    ;

    @Test
    public void insertValueInEmptyBoard_ReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(3, 1)), 2));
    }

    ;

    @Test
    public void insertValuewithSameValueOutsideRange_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 4), new ValueContent(2));
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(3, 4)), 2));
    }

    ;

    @Test
    public void insertValuewithSameValueinsideRange_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 1), new ValueContent(2));
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(2, 1)), 2));
    }

    ;

    @Test
    public void insertValuewithOtherValueinsideRange_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 2), new ValueContent(1));
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(2, 2)), 2));
    }

    ;

    @Test
    public void insertValuewithMultipleValuesinsideRange_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 3), new ValueContent(2));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(1));
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(2, 3)), 2));
    }

    ;
}
