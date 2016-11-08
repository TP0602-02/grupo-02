package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

public class DuplicatedValueUpRule extends ValidDuplicatedValueInMoveRule {
    @Override
    protected Cell move(Board board) {
        return this.iterator.moveUp(board,this.pivotCell);
    }
}
