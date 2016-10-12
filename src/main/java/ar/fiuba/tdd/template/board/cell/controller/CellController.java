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

    private InputUserView inputUserView;
    private UserInputListener userInputListener;

    public CellController() {
        inputUserView = new InputUserView();
    }

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
        inputUserView.setVisible(true);
        inputUserView.setListener(new InputUserView.UserInputListener() {
            @Override
            public void textInputed(String text) {
                CellController.this.textInputed(text);
            }
        });
        inputUserView.setBounds(0, 0, 300, 150);
        inputUserView.setVisible(true);


    }

    private void textInputed(String text) {
        if (userInputListener != null) {
            boolean validInput = userInputListener.validateUserTextInputed(this.model, text);
            if (validInput) {
                view.setText(text);
                //TODO habria que ver si la celda tiene muchos valores para saber que valor se cambio
                model.setContent(new ValueContent(text));
            }
        }

    }

    public void setUserInputListener(UserInputListener userInputListener) {
        this.userInputListener = userInputListener;
    }

    public interface UserInputListener {
        public boolean validateUserTextInputed(Cell cell, String text);
    }

}
