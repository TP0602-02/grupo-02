package ar.fiuba.tdd.template.puzzle.aggregators;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;
import ar.fiuba.tdd.template.entity.SpecialCharactersParser;

/**
 * Created by alazraqui on 18/10/2016.
 */
public class AggregatorWithConnections extends AbstractAgreggator {
    public AggregatorWithConnections() {
    }

    @Override
    public void runPlay(Play play, Board board) {
        Integer valueToAdd = SpecialCharactersParser.getInstance().getValueOf(play.getSelectedCellValue());
        getCellControllerOfCell(play.getSelectedCell()).addValue(valueToAdd.toString());
        Play newPLayToRun = getPlayFromCellConnection(play.getSelectedCell(), valueToAdd, board);
        if (newPLayToRun.getValidPlay()) {
            getCellControllerOfCell(newPLayToRun.getSelectedCell()).addValue(newPLayToRun.getSelectedCellValue());
        }
    }

    @Override
    public void deleteAction(Cell cell, String valueToDelete, Board board) {
        getCellControllerOfCell(cell).deletedValue(valueToDelete);
        Play newPLayToRun = getPlayFromCellConnection(cell, Integer.parseInt(valueToDelete), board);
        if (newPLayToRun.getValidPlay()) {
            getCellControllerOfCell(newPLayToRun.getSelectedCell()).deletedValue(newPLayToRun.getSelectedCellValue());
        }
    }


    private Play getPlayFromCellConnection(Cell cell, Integer valueOfConnection, Board board) {
        BoardIteratorConnections iterator = new BoardIteratorConnections();
        Cell nextCell = iterator.getNextCell(board,
                cell, valueOfConnection);
        Integer opositeDirection = iterator.getOppositeDirection(valueOfConnection);
        Play newPlay = new Play(nextCell, opositeDirection.toString());
        newPlay.setValidPlay(nextCell != null);
        return newPlay;
    }
}
