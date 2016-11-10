package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.Play;

public class ValidNumberRule extends GenericRule {
    @Override
    public boolean validate(Board board, Play play) {
        return (play.getValueOfCell() > 0 && play.getValueOfCell() < board.getHeight());
    }
}
