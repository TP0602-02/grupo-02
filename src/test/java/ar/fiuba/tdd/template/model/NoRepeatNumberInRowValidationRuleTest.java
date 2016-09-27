package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.board.cell.ValueContent;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.NoRepeatNumberInColumnValidationRule;
import ar.fiuba.tdd.template.rules.NoRepeatNumberInRowValidationRule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by alazraqui on 27/09/2016.
 */
public class NoRepeatNumberInRowValidationRuleTest {
    public Board board;
    public GenericRule rule;
    public Cell cell;

    @Before
    public void setUp() {
        this.board = new Board(4, 4);
        this.rule = new NoRepeatNumberInRowValidationRule();
        this.cell = this.board.getCell(0,0);
    }

    @Test
    public void testAddInEmptyBoard_ReturnTrue() {
        assertTrue(this.rule.validate(this.board, cell, 1));
    }

    @Test
    public void testAddWithOneNumberInBoard_ReturnTrue() {
        this.board.getCell(0,1).setContent((new ValueContent<Integer>(2)));
        assertTrue(this.rule.validate(this.board,cell,1));
    }

    @Test
    public void testAddWithOneNumberInBoard_ReturnFalse() {this.board.getCell(0,1).setContent((new ValueContent<Integer>(1)));
        assertTrue(!this.rule.validate(this.board,cell,1));
    }

    @Test
    public void testAddInCellWithNumberInBoard_ReturnFalse() {
        this.board.getCell(0,0).setContent((new ValueContent<Integer>(1)));
        this.board.getCell(0,2).setContent((new ValueContent<Integer>(2)));
        this.board.getCell(0,3).setContent((new ValueContent<Integer>(3)));
        assertTrue(!this.rule.validate(this.board,cell,1));
    }


}
