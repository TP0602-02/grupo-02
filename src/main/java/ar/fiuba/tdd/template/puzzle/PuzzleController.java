package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.cell.controller.CellController;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificator;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithBorders;
import ar.fiuba.tdd.template.entity.BaseController;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.SpecialCharactersParser;
import ar.fiuba.tdd.template.userinterface.view.PuzzleView;
import ar.fiuba.tdd.template.userinterface.view.WinGameView;
import ar.fiuba.tdd.template.winverificators.WinVerificator;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 27/09/16.
 */
public class PuzzleController extends BaseController<PuzzleView, Puzzle> {

    private ArrayList<CellController> cellControllers;
    private ArrayList<WinVerificator> winVerificators;
    private boolean addWithConnections;

    public PuzzleController(ArrayList<WinVerificator> winVerificators) {
        this.cellControllers = new ArrayList<>();
        this.winVerificators = winVerificators;
    }

    public void setAddWithConnections(boolean addWithConnections) {
        this.addWithConnections = addWithConnections;
    }

    @Override
    public void elementsAttached(PuzzleView view, Puzzle model) {
        createCellControllers(view, model);
    }

    private void createCellControllers(PuzzleView view, Puzzle model) {
        for (int column = 0; column < model.getBoardWidth(); ++column) {
            for (int row = 0; row < model.getBoardHeight(); ++row) {
                CellController cellController = new CellController();
                cellController.attachElements(view.getCellView(row, column),
                        model.getCell(new Coordinate(row, column)));
                cellController.setUserInputListener(new CellController.UserInputListener() {
                    @Override
                    public void validateUserTextInputed(Cell cell, String text) {

                        //TODO esto debe ser otra RULE que valide el dominio de los numeros posibles
                        if (text != null && text.length() == 1) {
                            Play play = new Play(cell, text);
                            if (model.checkMovement(play)) {
                                runPlay(play);
                                checkWinVerificator();
                            }
                        }
                    }

                    @Override
                    public void validateUserDeletedAction(Cell cell, String valueToDelete) {
                        getCellControllerOfCell(cell).deletedValue(valueToDelete);
                        //TODO hay que hacerlo levantando algo del archivo Y QUE EL PARSER SE LO SETEE AL PUZZLE CONTORLLER
                        if (addWithConnections) {
                            Play newPLayToRun = getPlayFromCellConnection(cell, valueToDelete);
                            if (newPLayToRun.getValidPlay()) {
                                getCellControllerOfCell(newPLayToRun.getSelectedCell()).deletedValue(newPLayToRun.getSelectedCellValue());
                            }
                        }
                    }
                });
                this.cellControllers.add(cellController);
            }
        }
    }

    private void checkWinVerificator() {
        boolean winGame = true;
        for (WinVerificator verificator : this.winVerificators) {
            winGame &= verificator.wonTheGame(this.model.getBoard());
        }
        if (winGame) {
            new WinGameView().setVisible(true);
        }
    }

    private void runPlay(Play play) {
        getCellControllerOfCell(play.getSelectedCell()).addValue(play.getSelectedCellValue());
        //TODO hay que hacerlo levantando algo del archivo Y QUE EL PARSER SE LO SETEE AL PUZZLE CONTORLLER
        if (addWithConnections) {
            Play newPLayToRun = getPlayFromCellConnection(play.getSelectedCell(), play.getSelectedCellValue());
            if (newPLayToRun.getValidPlay()) {
                getCellControllerOfCell(newPLayToRun.getSelectedCell()).addValue(newPLayToRun.getSelectedCellValue());
            }
        }
    }

    private CellController getCellControllerOfCell(Cell cell) {
        for (CellController cellController : this.cellControllers) {
            if (cellController.getModel().equals(cell)) {
                return cellController;
            }
        }
        return null;
    }

    private Play getPlayFromCellConnection(Cell cell, String valueOfConnection) {
        BoardIteratorConnections iterator = new BoardIteratorConnections();
        Cell nextCell = iterator.getNextCell(this.model.getBoard(),
                cell, SpecialCharactersParser.getInstance().getValueOf(valueOfConnection));

        String opositeDirection = iterator.getNameOppositeDirection(valueOfConnection);
        Play newPlay = new Play(nextCell, opositeDirection);
        newPlay.setValidPlay(nextCell != null);
        return newPlay;
    }
}
