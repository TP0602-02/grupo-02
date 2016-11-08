package ar.fiuba.tdd.template.board.cell.controller;

import ar.fiuba.tdd.template.board.InputUserView;
import ar.fiuba.tdd.template.board.cell.ShowCellValuesView;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.board.cell.view.CellView;
import ar.fiuba.tdd.template.drawers.DrawerFactory;
import ar.fiuba.tdd.template.entity.BaseController;
import ar.fiuba.tdd.template.puzzle.PuzzleController;


/**
 * Created by Nicolas on 27/9/2016.
 */
public class CellController extends BaseController<CellView, Cell> {

    private UserInputListener userInputListener;

    public CellController(PuzzleController puzzleController) {
        super();
        this.setUserInputListener(new CellController.UserInputListener() {
            @Override
            public void validateUserTextInputed(Cell cell, String text) {
                puzzleController.validateAndPlay(cell,text);
            }

            @Override
            public void validateUserDeletedAction(Cell cell, String valueToDelete) {
                puzzleController.validateAndDelete(cell, valueToDelete);
            }
        });
    }

    @Override
    public void elementsAttached(CellView cellView, Cell cell) {
        if ( cellView != null ) {
            cellView.setListener(new CellView.ClickCellListener() {
                @Override
                public void onClickForWrite() {
                    if (cell.isEditable()) {
                        showMessageInput();
                    }
                }

                @Override
                public void onClickForRead() {
                    showWindowWithValues();
                }
            });
        }
    }

    private void showWindowWithValues() {
        ShowCellValuesView.getInstance().showValues(this.model.getContents());
    }

    public void showMessageInput() {
        InputUserView.getInstance().showInputUserView();
        InputUserView.getInstance().setCellValuesToDelete(this.model.getDeleteableValues());
        InputUserView.getInstance().setListener(new InputUserView.UserInputListener() {
            @Override
            public void inputedText(String text) {
                CellController.this.textInputed(text);
            }

            @Override
            public void deletedValue(String text) {
                deletedInputed(text);
            }
        });
    }

    public void deletedValue(String text) {
        if (this.model.isEditable()) {
            this.model.removeContentWithValue(text);
            if ( this.getView() != null ) {
                DrawerFactory.getInstance().getDrawer().draw(view,model.getShowableValues());
            }
            //view.setValues(model.getShowableValues());
        }
    }

    private void textInputed(String text) {
        if (userInputListener != null) {
            userInputListener.validateUserTextInputed(this.model, text);
        }

    }

    private void deletedInputed(String text) {
        if (userInputListener != null) {
            userInputListener.validateUserDeletedAction(this.model, text);
        }
    }

    public void setUserInputListener(UserInputListener userInputListener) {
        this.userInputListener = userInputListener;
    }

    public void addValue(String value) {
        if (this.model.isEditable()) {
            model.setContent(new ValueContent(value));
            if ( this.getView() != null ) {
                DrawerFactory.getInstance().getDrawer().draw(view,model.getShowableValues());
            }

            // view.setValues(model.getShowableValues());
        }
    }


    public interface UserInputListener {
        public void validateUserTextInputed(Cell cell, String text);

        public void validateUserDeletedAction(Cell cell, String valueToDelete);
    }

}
