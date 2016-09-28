package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.board.cell.ValueContent;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.NoRepeatNumberInRowValidationRule;
import ar.fiuba.tdd.template.rules.NoRepeatNumberInSquareRegionValidationRule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by alazraqui on 27/09/2016.
 */
public class NoRepeatNumberInSquareRegionValidationRuleTest {

    public Board board;
    public GenericRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4);
        this.rule = new NoRepeatNumberInSquareRegionValidationRule();
    }

    @Test
    public void addInEmptyBoard_ReturnTrue() {
        assertTrue(this.rule.validate(this.board,this.board.getCell(0,0),1));
    }

    @Test
    public void addNumberInRegionAlreadyExists_ReturnFalse() {
        this.board.getCell(0,1).setContent(new ValueContent<Integer>(1));
        assertTrue(!this.rule.validate(this.board,this.board.getCell(0,0),1));
    }

    @Test
    public void addNumberInOtherRegion_ReturnTrue() {
        this.board.getCell(1,1).setContent((new ValueContent<Integer>(1)));
        assertTrue(this.rule.validate(this.board,this.board.getCell(2,2),1));
    }

}











