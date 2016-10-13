package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.rules.CellHasValidConectionsRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by alazraqui on 13/10/2016.
 */
public class CellHasValidConectionsRuleTest {
    private Board board;
    private CellHasValidConectionsRule rule;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;

    @Before
    public void setUp() {
        this.board = new Board(9, 9, "");
        this.rule = new CellHasValidConectionsRule();
        this.board.setValue(0, 0, new ValueContent(RIGHT));
        this.board.setValue(0, 1, new ValueContent(LEFT));
        this.board.setValue(0, 1, new ValueContent(DOWN));
        this.board.setValue(1, 1, new ValueContent(UP));
    }

    @Test
    public void conectTwoCellsWithNoConections_ReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(5, 5), RIGHT));
    }

    @Test
    public void connectCellWithOneValuesWithCellWithOneValue_ReturnTrue() {
        this.board.setValue(1, 0, new ValueContent(DOWN));
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(1, 1), RIGHT));
    }

    @Test
    public void connectCellWithNoValuesWithCellWithOneValue_ReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(1, 2), LEFT));
    }

    @Test
    public void connectCellWithOneValuesWithCellWithNoValue_ReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(1, 2), LEFT));
    }

    @Test
    public void connectCellWithNoValuesWithCellWithTwoValues_ReturnFalse() {
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(0, 2), LEFT));
    }

    @Test
    public void connectCellWithTwoValuesWithCellWithNoValue_ReturnFalse() {
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(0, 1), RIGHT));
    }

    @Test
    public void connectCellWithOneValuesWithCellWithTwoValues_ReturnFalse() {
        this.board.setValue(0, 2, new ValueContent(RIGHT));
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(0, 2), LEFT));
    }

    @Test
    public void connectCellWithTwoValuesWithCellWithTwoValues_ReturnFalse() {
        this.board.setValue(0, 2, new ValueContent(RIGHT));
        this.board.setValue(0, 2, new ValueContent(DOWN));
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(0, 2), LEFT));
    }
}
