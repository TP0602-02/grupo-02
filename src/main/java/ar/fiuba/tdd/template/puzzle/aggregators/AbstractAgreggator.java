package ar.fiuba.tdd.template.puzzle.aggregators;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.controller.CellController;
import ar.fiuba.tdd.template.board.cell.model.Cell;

import java.util.ArrayList;

/**
 * Created by alazraqui on 18/10/2016.
 */
public abstract class AbstractAgreggator {
    protected ArrayList<CellController> cellControllers;

    public abstract void runPlay(Play play, Board board);

    public abstract void deleteAction(Cell cell, String valueToDelete, Board board);

    protected CellController getCellControllerOfCell(Cell cell) {
        for (CellController cellController : this.cellControllers) {
            if (cellController.getModel().equals(cell)) {
                return cellController;
            }
        }
        return null;
    }

    public void setCellControllers(ArrayList<CellController> cellControllers) {
        this.cellControllers = cellControllers;
    }
}
