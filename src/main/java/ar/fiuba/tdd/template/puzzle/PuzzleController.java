package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.cell.controller.CellController;
import ar.fiuba.tdd.template.board.cell.model.Cell;

import ar.fiuba.tdd.template.board.cell.model.CellContent;
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
import javax.swing.*;



/**
 * Created by matiaskamien on 27/09/16.
 */
public class PuzzleController extends BaseController<PuzzleView, Puzzle> {

   // private static final String OUTPUT_FILE_ROOT = "src/json/PlayOutput.json";
    private ArrayList<CellController> cellControllers;
    private ArrayList<WinVerificator> winVerificators;
    private AbstractAgreggator aggregator;

    private static final String OUTPUT_FILE_ROOT = "src/json/";
    private static final String OUTPUT_FILE_FORMAT = "Output.json";

    public PuzzleController(ArrayList<WinVerificator> winVerificators, AbstractAgreggator aggregator) {
        this.cellControllers = new ArrayList<>();
        this.winVerificators = winVerificators;
        this.aggregator = aggregator;
    }

    public void aggregateCellControllers() {
        this.aggregator.setCellControllers(this.cellControllers);
    }

    @Override
    public void elementsAttached(PuzzleView view, Puzzle model) {
        createCellControllers(view, model);
        undoConfig();
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
                        if (text != null && text.length() == 1) {
                            Play play = new Play(cell, text);
                            boolean itsPlayed = model.checkMovement(play);
                            if (itsPlayed) {
                                runPlay(play);
                                checkWinVerificator();
                            }
                        }
                    }

                    @Override
                    public void validateUserDeletedAction(Cell cell, String valueToDelete) {
                        deleteAction(cell, valueToDelete);
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
            JOptionPane.showMessageDialog(null, "Felicitaciones has ganado!");
        }
    }

    private void runPlay(Play play) {
        this.aggregator.runPlay(play, this.model.getBoard());
    }

    public void deleteAction(Cell cell, String valueToDelete) {
        this.aggregator.deleteAction(cell, valueToDelete, this.model.getBoard());
    }

    public void execPlays(ArrayList<Play> plays, String gameName) {
        FileWriter fileWriter = new FileWriter();
        ArrayList<Play> playsToWrite = new ArrayList<>();
        for (Play play : plays) {
            Cell cellInBoard = model.getCell(new Coordinate(play.getSelectedCell().getRow(),
                    play.getSelectedCell().getColumn()));
            Play newPlay = new Play(cellInBoard, play.getSelectedCellValue());
            if (model.checkMovement(newPlay)) {
                runPlay(newPlay);
                checkWinVerificator();
            }
            playsToWrite.add(newPlay);
        }
        fileWriter.writePlayResults(playsToWrite, OUTPUT_FILE_ROOT + gameName.toLowerCase().replaceAll("\\s+","") + OUTPUT_FILE_FORMAT);
    }

    private void undoConfig() {
        Undo.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (!aggregator.stackHasNoPlays()) {
                    aggregator.undo(model.getBoard());
                }
            }
        });
    }
}
