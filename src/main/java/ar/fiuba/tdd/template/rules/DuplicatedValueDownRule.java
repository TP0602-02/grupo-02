package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

public class DuplicatedValueDownRule extends ValidDuplicatedValueInMoveRule {
    @Override
    protected Cell move(Board board) {
        return this.iterator.moveDown(board,this.pivotCell);
    }
}
