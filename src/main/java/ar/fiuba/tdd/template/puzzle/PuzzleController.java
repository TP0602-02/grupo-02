package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.cell.controller.CellController;
import ar.fiuba.tdd.template.board.cell.model.Cell;

import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.view.CellView;
import ar.fiuba.tdd.template.entity.BaseController;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.FileWriter;
import ar.fiuba.tdd.template.puzzle.aggregators.AbstractAgreggator;
import ar.fiuba.tdd.template.userinterface.view.PuzzleView;

import ar.fiuba.tdd.template.userinterface.view.Undo;
import ar.fiuba.tdd.template.winverificators.WinVerificator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;



/**
 * Created by matiaskamien on 27/09/16.
 */
public class PuzzleController extends BaseController<PuzzleView, Puzzle> {

    private static final String OUTPUT_FILE_ROOT = "src/json/PlayOutput.json";
    private ArrayList<CellController> cellControllers;
    private ArrayList<WinVerificator> winVerificators;
    private AbstractAgreggator aggregator;
    private ArrayList<Play> playStack;
    private static final String BORRADO = "Borrado";
    private boolean vizualizate;

    public PuzzleController(ArrayList<WinVerificator> winVerificators, AbstractAgreggator aggregator) {
        this.cellControllers = new ArrayList<>();
        this.winVerificators = winVerificators;
        this.aggregator = aggregator;
        playStack = new ArrayList<Play>();
    }

    public void aggregateCellControllers() {
        this.aggregator.setCellControllers(this.cellControllers);
    }

    @Override
    public void elementsAttached(PuzzleView view, Puzzle model) {
        vizualizate = (view != null);
        createCellControllers(view, model);
    }

    private void createCellControllers(PuzzleView view, Puzzle model) {
        for (int column = 0; column < model.getBoardWidth(); ++column) {
            for (int row = 0; row < model.getBoardHeight(); ++row) {
                CellController cellController = new CellController(this);
                CellView cellView = null;
                if (isVizualizable()) {
                    cellView = view.getCellView(row, column);
                }
                cellController.attachElements(cellView,model.getCell(new Coordinate(row, column)));
                this.cellControllers.add(cellController);
            }
        }
    }

    public void validateAndDelete(Cell cell, String valueToDelete) {
        if (cell.hasDeleteables()) {
            playStack.add(0, new Play(cell,BORRADO));
        }
        deleteAction(cell, valueToDelete);
    }

    public void validateAndPlay(Cell cell, String text) {
        if (text != null && text.length() == 1) {
            Play play = new Play(cell, text);
            boolean itsPlayed = playThePlay(play);
            if (itsPlayed) {
                playStack.add(0, play);
            }
        }
    }

    private boolean playThePlay(Play play) {
        boolean itsPlayed = model.checkMovement(play);
        if (itsPlayed) {
            runPlay(play);
            checkWinVerificator();
        }
        return itsPlayed;
    }

    private void checkWinVerificator() {
        boolean winGame = true;
        for (WinVerificator verificator : this.winVerificators) {
            winGame &= verificator.wonTheGame(this.model.getBoard());
        }
        if (winGame) {
            this.getView().showWinMessage();
        }
    }

    private void runPlay(Play play) {
        this.aggregator.runPlay(play, this.model.getBoard());
    }

    public void deleteAction(Cell cell, String valueToDelete) {
        this.aggregator.deleteAction(cell, valueToDelete, this.model.getBoard());
    }

    public void execPlays(ArrayList<Play> plays) {
        FileWriter fileWriter = new FileWriter();
        ArrayList<Play> playsToWrite = new ArrayList<>();
        for (Play play : plays) {
            Cell cellInBoard = model.getCell(new Coordinate(play.getSelectedCell().getRow(),
                    play.getSelectedCell().getColumn()));
            Play newPlay = new Play(cellInBoard, play.getSelectedCellValue());
            if (model.checkMovement(newPlay)) {
                runPlay(newPlay);
                //checkWinVerificator();
            }
            playsToWrite.add(newPlay);
        }
        fileWriter.writePlayResults(playsToWrite, OUTPUT_FILE_ROOT);
    }

    private void undoConfig() {
        Undo.config(this);
    }

    public void undoPlay() {
        if (!playStack.isEmpty()) {
            Play playToUndo = playStack.get(0);
            playStack.remove(0);
            Cell cellToUndo = playToUndo.getSelectedCell();
            ArrayList<CellContent> contents = cellToUndo.getContents();
            eraseTheLastPlay(contents,cellToUndo,playToUndo.getSelectedCellValue());
            addThePriorPlay(playToUndo);
        }
    }

    private void eraseTheLastPlay(ArrayList<CellContent> contents, Cell cellToUndo, String selectedCellValue) {
        int counterOfNonDeleteable = 0;
        while (contents.size() != counterOfNonDeleteable) {
            CellContent cellContentToUndo = contents.get(contents.size() - 1);
            if (cellContentToUndo.isDeleteable() && Objects.equals(cellContentToUndo.getValue(),selectedCellValue)) {
                deleteAction(cellToUndo, cellContentToUndo.getValue());
            } else {
                counterOfNonDeleteable++;
            }
        }
    }

    private void addThePriorPlay(Play playToUndo) {
        Play priorPlay = getPriorPlayValue(playToUndo);
        if (priorPlay != null && !(priorPlay.getSelectedCellValue()).equals(playToUndo.getSelectedCellValue())) {
            if (!priorPlay.getSelectedCellValue().equals(BORRADO)) {
                playThePlay(priorPlay);
            }
        }
    }

    private Play getPriorPlayValue(Play playToUndo) {
        Play priorPlay = null;
        for (Play oldPlay : playStack) {
            if (playToUndo.getSelectedCell() == oldPlay.getSelectedCell()) {
                return oldPlay;
            }
        }
        return priorPlay;
    }

    public boolean isVizualizable() {
        return vizualizate;
    }
}
