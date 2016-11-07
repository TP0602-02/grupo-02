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
        int validNumber = SpecialCharactersParser.getInstance().getValueOf(play.getSelectedCellValue());
        ArrayList<Integer> numbersToAdd = transformValueToCorners(validNumber);
        for (Integer number : numbersToAdd) {
            getCellControllerOfCell(play.getSelectedCell()).addValue(number.toString());
        }
    }

    @Override
    public void undo(Board board) {

    }

    private ArrayList<Integer> transformValueToCorners(int validNumber) {
        ArrayList<Integer> corners = new ArrayList<Integer>();
        switch (validNumber) {
            case 1:
            case 4:
                corners.add(Constants.UPPER_LEFT_CORNER);
                corners.add(Constants.LOWER_RIGHT_CORNER);
                return corners;
            case 2:
            case 3:
                corners.add(Constants.LOWER_LEFT_CORNER);
                corners.add(Constants.UPPER_RIGHT_CORNER);
                return corners;
            default:
                return corners;
        }
    }

    @Override
    public void deleteAction(Cell cell, String valueToDelete, Board board) {
        ArrayList<Integer> numbersToAdd = transformValueToCorners(Integer.parseInt(valueToDelete));
        for (Integer number : numbersToAdd) {
            getCellControllerOfCell(cell).deletedValue(number.toString());
        }
    }

    @Override
    public void addPlayToStack(Play play) {

    }
}
