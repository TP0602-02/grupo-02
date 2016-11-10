package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.entity.Play;

/**
 * Created by alazraqui on 03/11/2016.
 */
public class NoRepeatValueInDistanceRule extends GenericRule {
    private DuplicatedValueToLeftRule leftRule;
    private DuplicatedValueUpRule upRule;
    private DuplicatedValueDownRule downRule;
    private DuplicatedValueToRightRule rightRule;

    @Override
    public boolean validate(Board board, Play play) {
        boolean horizontal = leftRule.validate(board, play) && rightRule.validate(board, play);
        boolean vertical = upRule.validate(board, play) && downRule.validate(board, play);
        return horizontal && vertical;
    }

    public NoRepeatValueInDistanceRule() {
        this.leftRule = new DuplicatedValueToLeftRule();
        this.rightRule = new DuplicatedValueToRightRule();
        this.upRule = new DuplicatedValueUpRule();
        this.downRule = new DuplicatedValueDownRule();
    }
}
