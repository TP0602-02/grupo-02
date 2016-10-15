package ar.fiuba.tdd.template.board.cell.controller;

import ar.fiuba.tdd.template.board.InputUserView;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.board.cell.view.CellView;
import ar.fiuba.tdd.template.entity.BaseController;


/**
 * Created by Nicolas on 27/9/2016.
 */
public class CellController extends BaseController<CellView, Cell> {

    private UserInputListener userInputListener;

    @Override
    public void elementsAttached(CellView cellView, Cell cell) {
        if (cell.isEditable()) {
            cellView.setListener(new CellView.ClickCellListener() {
                @Override
                public void onClick() {
                    showMessageInput();
                }
            });
        }
    }

    public void showMessageInput() {
        InputUserView.getInstance().showInputUserView();
        InputUserView.getInstance().setCellValuesToDelete(this.model.getValuesToString());
        InputUserView.getInstance().setListener(new InputUserView.UserInputListener() {
            @Override
            public void inputedText(String text) {
                CellController.this.textInputed(text);
            }

            @Override
            public void deletedValue(String text) {
                CellController.this.deletedValue(text);
            }
        });
    }

    private void deletedValue(String text) {
        this.model.removeContentWithValue(text);
        view.setValues(model.getValuesToString());
    }

    private void textInputed(String text) {
        if (userInputListener != null) {
            userInputListener.validateUserTextInputed(this.model, text);
        }

    }

    public void setUserInputListener(UserInputListener userInputListener) {
        this.userInputListener = userInputListener;
    }

    public interface UserInputListener {
        public void validateUserTextInputed(Cell cell, String text);
    }

    public void addValue(String value) {
        model.setContent(new ValueContent(value));
        view.setValues(model.getValuesToString());
    }

}
