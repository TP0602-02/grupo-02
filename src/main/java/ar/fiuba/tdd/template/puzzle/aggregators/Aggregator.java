package ar.fiuba.tdd.template.puzzle.aggregators;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.controller.CellController;
import ar.fiuba.tdd.template.board.cell.model.Cell;

import java.util.ArrayList;

/**
 * Created by alazraqui on 18/10/2016.
 */
public class Aggregator extends AbstractAgreggator {

    public Aggregator() {
    }

    public void runPlay(Play play, Board board) {
        getCellControllerOfCell(play.getSelectedCell()).addValue(play.getSelectedCellValue());
    }

    @Override
    public void deleteAction(Cell cell, String valueToDelete, Board board) {
        getCellControllerOfCell(cell).deletedValue(valueToDelete);
    }
}
