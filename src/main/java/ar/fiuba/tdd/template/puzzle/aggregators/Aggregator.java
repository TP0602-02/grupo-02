package ar.fiuba.tdd.template.puzzle.aggregators;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;

/**
 * Created by alazraqui on 18/10/2016.
 */
public class Aggregator extends AbstractAgreggator {

    public Aggregator() {
    }

    public void runPlay(Play play, Board board) {
        getCellControllerOfCell(play.getSelectedCell()).addValue(play.getSelectedCellValue());
        addPlayToStack(play);
    }

    @Override
    public void undo(Board board) {
        Play play = this.playStack.get(0);
        this.deleteAction(play.getSelectedCell(),play.getSelectedCellValue(),board);

    }

    @Override
    public void deleteAction(Cell cell, String valueToDelete, Board board) {
        getCellControllerOfCell(cell).deletedValue(valueToDelete);
        removeEspecificPlayOfStack(cell, valueToDelete);
    }
}
