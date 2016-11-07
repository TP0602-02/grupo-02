package ar.fiuba.tdd.template.puzzle.aggregators;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.controller.CellController;
import ar.fiuba.tdd.template.board.cell.model.Cell;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by alazraqui on 18/10/2016.
 */
public abstract class AbstractAgreggator {
    protected ArrayList<CellController> cellControllers;
    protected ArrayList<Play> playStack;

    public abstract void runPlay(Play play, Board board);

    public boolean stackHasNoPlays() {
        return playStack.size() == 0;
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
        this.playStack.add(0, play);
    }

    public void removePlayOfStack() {
        if (!stackHasNoPlays()) {
            this.playStack.remove(0);
        }
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
