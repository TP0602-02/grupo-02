package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.cell.controller.CellController;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.BaseController;
import ar.fiuba.tdd.template.entity.SpecialCharactersParser;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.userinterface.view.PuzzleView;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 27/09/16.
 */
public class PuzzleController extends BaseController<PuzzleView, Puzzle> {

    private ArrayList<CellController> cellControllers;

    public PuzzleController() {
        this.cellControllers = new ArrayList<>();
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
                        model.getCell(row, column));
                cellController.setUserInputListener(new CellController.UserInputListener() {
                    @Override
                    public boolean validateUserTextInputed(Cell cell, String text) {
                        int textParsed = SpecialCharactersParser.getInstance().getValueOf(text);
                       /*
                        //TODO esto debe ser otra RULE que valide el dominio de los numeros posibles
                        if(textParsed > 0 && textParsed <= model.getBoardHeight()){
                            return model.checkMovement(cell,textParsed);
                        }else{
                            return false;
                        }*/
                        Play play = new Play(cell);
                        play.setSelectedValue(textParsed);
                        return model.checkMovement(play);
                    }
                });
                this.cellControllers.add(cellController);
            }
        }
    }
}
