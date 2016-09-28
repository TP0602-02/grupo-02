package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.BlackContent;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.board.cell.ClueContent;
import ar.fiuba.tdd.template.board.cell.ValueContent;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.NoRepeatNumberInRegionValidationRule;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by matiaskamien on 27/09/16.
 */
public class NoRepeatNumberInRegionValidationRuleTest {

    public Board board;
    public GenericRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4);
        this.rule = new NoRepeatNumberInRegionValidationRule();
    }

    @Test
    public void addInEmptyKakuroBoard_ReturnTrue() {
        this.board.getCell(0,0).setContent(new BlackContent());
        this.board.getCell(0,1).setContent(new ClueContent<Integer>(8));
        this.board.getCell(1,1).setContent(new ValueContent<Integer>(3));
        this.board.getCell(3,1).setContent(new BlackContent());
        this.board.getCell(2,0).setContent(new BlackContent());
        this.board.getCell(3,0).setContent(new BlackContent());
        this.board.getCell(1,0).setContent(new ClueContent<Integer>(8));
        this.board.getCell(1,2).setContent(new ClueContent<Integer>(5));
        this.board.getCell(1,3).setContent(new BlackContent());
        this.board.getCell(2,3).setContent(new BlackContent());
        this.board.getCell(2,2).setContent(new ValueContent<Integer>(7));
        assertTrue(this.rule.validate(this.board,this.board.getCell(2,1),4));
    }

}
