package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.NoRepeatNumberInRowValidationRule;
import ar.fiuba.tdd.template.rules.NumberValidationRule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by alazraqui on 27/09/2016.
 */
public class NumberValidationRuleTest {

    public Board board;
    public GenericRule rule;
    public Cell cell;

    @Before
    public void setUp() {
        this.board = new Board(4, 4);
        this.rule = new NumberValidationRule(1,4);
        this.cell = this.board.getCell(0,0);
    }

    @Test
    public void numberToAddHigherThanLimit() {
        assertTrue(!this.rule.validate(board, cell,5));
    }

    @Test
    public void numberToAddLowerThanLimit() {
        assertTrue(!this.rule.validate(board, cell,0));
    }

    @Test
    public void numberToAddInMediumOk() {
        assertTrue(this.rule.validate(board, cell,3));
    }

    @Test
    public void numberToAddInLimitOk() {
        assertTrue(this.rule.validate(board, cell,1));
    }




}
