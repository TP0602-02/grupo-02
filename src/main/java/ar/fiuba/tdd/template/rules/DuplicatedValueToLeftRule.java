package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by alazraqui on 02/11/2016.
 */
public class DuplicatedValueToLeftRule extends ValidDuplicatedValueInMoveRule {
    @Override
    protected Cell move(Board board) {
        return this.iterator.moveLeft(board,this.pivotCell);
    }
}
