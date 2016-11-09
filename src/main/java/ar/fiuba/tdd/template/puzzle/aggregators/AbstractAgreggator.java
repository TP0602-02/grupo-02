package ar.fiuba.tdd.template.puzzle.aggregators;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.controller.CellController;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.Play;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alazraqui on 18/10/2016.
 */
public abstract class AbstractAgreggator {
    private static final int STACK_EMPTY = 0;
    protected static final int FIRST_POSITION_IN_STACK = 0;
    protected ArrayList<CellController> cellControllers;
    protected ArrayList<Play> playStack;

    public abstract void runPlay(Play play, Board board);

    public boolean stackHasNoPlays() {
        return playStack.size() == STACK_EMPTY;
    }

    public abstract void undo(Board board);

    public abstract void deleteAction(Cell cell, String valueToDelete, Board board);

    protected CellController getCellControllerOfCell(Cell cell) {
        for (CellController cellController : this.cellControllers) {
            if (cellController.getModel().equals(cell)) {
                return cellController;
            }
        }
        return null;
    }

    public void addPlayToStack(Play play) {
        int firstPosition = FIRST_POSITION_IN_STACK;
        this.playStack.add(firstPosition, play);
    }

    public void removeEspecificPlayOfStack(Cell cell, String valueToDelete) {
        for (Iterator<Play> iterator = this.playStack.iterator(); iterator.hasNext();) {
            Play play = iterator.next();
            if (play.getSelectedCell() == cell && play.getSelectedCellValue().equals(valueToDelete)) {
                iterator.remove();
            }
        }
    }

    public void setCellControllers(ArrayList<CellController> cellControllers) {
        this.cellControllers = cellControllers;
        this.playStack = new ArrayList<Play>();
    }
}
