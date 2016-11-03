package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by alazraqui on 03/11/2016.
 */
public class NoRepeatValueInDistanceRule extends GenericRule {
    private DuplicatedValueToLeftRule leftRule;
    private DuplicatedValueUpRule upRule;
    private DuplicatedValueDownRule downRule;
    private DuplicatedValueToRightRule rightRule;

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        boolean horizontal = leftRule.validate(board,cell,numberToAdd) && rightRule.validate(board,cell,numberToAdd);
        boolean vertical = upRule.validate(board,cell,numberToAdd) && downRule.validate(board,cell,numberToAdd);
        return horizontal && vertical;
    }

    public NoRepeatValueInDistanceRule() {
        this.leftRule = new DuplicatedValueToLeftRule();
        this.rightRule = new DuplicatedValueToRightRule();
        this.upRule = new DuplicatedValueUpRule();
        this.downRule = new DuplicatedValueDownRule();
    }
}
