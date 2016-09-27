package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;
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
    public Cell cell;

    @Before
    public void setUp() {
        this.board = new Board(4, 4);
        this.rule = new NoRepeatNumberInRegionValidationRule();
        this.cell = this.board.getCell(0, 0);
    }

}
