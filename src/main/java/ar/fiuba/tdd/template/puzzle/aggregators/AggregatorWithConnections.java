package ar.fiuba.tdd.template.puzzle.aggregators;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.controller.CellController;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;
import ar.fiuba.tdd.template.entity.SpecialCharactersParser;

import java.util.ArrayList;

/**
 * Created by alazraqui on 18/10/2016.
 */
public class AggregatorWithConnections extends AbstractAgreggator {
    public AggregatorWithConnections() {
    }

    @Override
    public void runPlay(Play play, Board board) {
        getCellControllerOfCell(play.getSelectedCell()).addValue(play.getSelectedCellValue());
        Play newPLayToRun = getPlayFromCellConnection(play.getSelectedCell(), play.getSelectedCellValue(), board);
        if (newPLayToRun.getValidPlay()) {
            getCellControllerOfCell(newPLayToRun.getSelectedCell()).addValue(newPLayToRun.getSelectedCellValue());
        }
    }

    @Override
    public void deleteAction(Cell cell, String valueToDelete, Board board) {
        getCellControllerOfCell(cell).deletedValue(valueToDelete);
        Play newPLayToRun = getPlayFromCellConnection(cell, valueToDelete, board);
        if (newPLayToRun.getValidPlay()) {
            getCellControllerOfCell(newPLayToRun.getSelectedCell()).deletedValue(newPLayToRun.getSelectedCellValue());
        }
    }


    private Play getPlayFromCellConnection(Cell cell, String valueOfConnection, Board board) {
        BoardIteratorConnections iterator = new BoardIteratorConnections();
        Cell nextCell = iterator.getNextCell(board,
                cell, SpecialCharactersParser.getInstance().getValueOf(valueOfConnection));
        String opositeDirection = iterator.getNameOppositeDirection(valueOfConnection);
        Play newPlay = new Play(nextCell, opositeDirection);
        newPlay.setValidPlay(nextCell != null);
        return newPlay;
    }
}
