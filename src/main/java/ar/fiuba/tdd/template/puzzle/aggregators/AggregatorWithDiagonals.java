package ar.fiuba.tdd.template.puzzle.aggregators;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.Constants;
import ar.fiuba.tdd.template.entity.SpecialCharactersParser;

import java.util.ArrayList;

/**
 * Created by alazraqui on 19/10/2016.
 */
public class AggregatorWithDiagonals extends AbstractAgreggator {
    @Override
    public void runPlay(Play play, Board board) {
        getCellControllerOfCell(play.getSelectedCell()).addValue(play.getSelectedCellValue());
    }

    @Override
    public void deleteAction(Cell cell, String valueToDelete, Board board) {
        getCellControllerOfCell(cell).deletedValue(valueToDelete);
    }
}
