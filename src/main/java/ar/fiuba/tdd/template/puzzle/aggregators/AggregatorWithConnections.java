package ar.fiuba.tdd.template.puzzle.aggregators;


import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;
import ar.fiuba.tdd.template.entity.Play;
import ar.fiuba.tdd.template.entity.SpecialCharactersParser;

/**
 * Created by alazraqui on 18/10/2016.
 */
public class AggregatorWithConnections extends AbstractAgreggator {
    public AggregatorWithConnections() {
    }

    @Override
    public void runPlay(Play play, Board board) {
        getCellControllerOfCell(play.getSelectedCell()).addValue(play.getSelectedCellValue());
        addPlayToStack(play);
        Play newPLayToRun = getPlayFromCellConnection(play.getSelectedCell(), play.getSelectedCellValue(), board);
        if (newPLayToRun.getValidPlay()) {
            addPlayToStack(newPLayToRun);
            getCellControllerOfCell(newPLayToRun.getSelectedCell()).addValue(newPLayToRun.getSelectedCellValue());
        }
    }

    @Override
    public void undo(Board board) {
        Play play = playStack.get(FIRST_POSITION_IN_STACK);
        deleteAction(play.getSelectedCell(),play.getSelectedCellValue(),board);
    }

    @Override
    public void deleteAction(Cell cell, String valueToDelete, Board board) {
        getCellControllerOfCell(cell).deletedValue(valueToDelete);
        Play newPLayToRun = getPlayFromCellConnection(cell, valueToDelete, board);
        removeEspecificPlayOfStack(cell,valueToDelete);
        if (newPLayToRun.getValidPlay()) {
            getCellControllerOfCell(newPLayToRun.getSelectedCell()).deletedValue(newPLayToRun.getSelectedCellValue());
            removeEspecificPlayOfStack(newPLayToRun.getSelectedCell(),newPLayToRun.getSelectedCellValue());
        }
    }

    private Play getPlayFromCellConnection(Cell cell, String valueOfConnection, Board board) {
        BoardIteratorConnections iterator = new BoardIteratorConnections();
        Integer valueToAdd = SpecialCharactersParser.getInstance().getValueOf(valueOfConnection);

        Cell nextCell = iterator.getNextCell(board,
                cell, valueToAdd);
        String opositeDirectionString = iterator.getNameOppositeDirection(valueOfConnection);
        Play newPlay = new Play(nextCell, opositeDirectionString);
        newPlay.setValidPlay(nextCell != null);
        return newPlay;
    }
}
