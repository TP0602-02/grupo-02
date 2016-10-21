package ar.fiuba.tdd.template.board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;


/**
 * Created by Nicolas on 28/9/2016.
 */

public class InputUserView extends JFrame {

    private static InputUserView instance;

    public static final int buttonPerRow = 3;
    public static final int buttonValuesWidth = 50;
    public static final int spaceBetweenButtons = 15;
    public static final int firstButtonCoordinateX = 10;
    public static final int firstButtonCoordinateY = 80;
    public static final int inputUserViewWidth = 450;
    public static final int inputUserViewHeight = 600;

    private JTextField textInput;
    private JButton botonOk;
    JButton backspaceButton;
    JButton deleteContentButton;
    private UserInputListener listener;
    private ArrayList<String> allowedValuesToInput;
    private ArrayList<String> cellValuesToDelete;
    private JPanel valuesToDeleteContainer;
    private String selectedValueToDelete;


    private InputUserView(ArrayList<String> allowedValuesToInput) {
        this.allowedValuesToInput = allowedValuesToInput;
        this.selectedValueToDelete = "";
        setLayout(null);
        textInput = new JTextField();
        textInput.setEditable(false);
        textInput.setBounds(firstButtonCoordinateX, firstButtonCoordinateY - 50, 150, 20);
        add(textInput);
        valuesToDeleteContainer = new JPanel();
        valuesToDeleteContainer.setBounds(inputUserViewWidth / 2, 10, inputUserViewWidth / 2, inputUserViewHeight / 2);
        add(valuesToDeleteContainer);
        initButtonsView();
        botonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                inputTextIsReady();
            }
        });
        setMinimumSize(new Dimension(inputUserViewWidth, inputUserViewHeight));
    }

    public static InputUserView createView(ArrayList<String> allowedValuesToInput) {
        if (instance == null) {
            instance = new InputUserView(allowedValuesToInput);
        }
        return instance;
    }

    /**
     * Firstable must call createView method.
     *
     * @return instance of InputUserView initialized.
     */
    public static InputUserView getInstance() {
        return instance;
    }


    public void setCellValuesToDelete(ArrayList<String> cellValuesToDelete) {

        this.cellValuesToDelete = cellValuesToDelete;
        valuesToDeleteContainer.removeAll();
        for (String value : cellValuesToDelete) {
            JButton button = new JButton(value);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    selectedValueToDelete = button.getText();
                }
            });
            valuesToDeleteContainer.add(button);
        }
        JButton buttonDeleteOk = new JButton("BORRAR!");
        buttonDeleteOk.setBackground(Color.white);
        buttonDeleteOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (!selectedValueToDelete.isEmpty()) {
                    deleteCellContent();
                } else {
                    //TODO mostrar mensaje que debe seleccionar primero un vaclor y luego pulsar OK
                }
                selectedValueToDelete = "";

            }
        });
        valuesToDeleteContainer.add(buttonDeleteOk);
    }

    public void showInputUserView() {
        setVisible(true);
        selectedValueToDelete = "";
        valuesToDeleteContainer.setVisible(false);
    }

    private void hideInputUserView() {
        setVisible(false);
        valuesToDeleteContainer.setVisible(false);
    }

    public void onClickDeleteCellContent() {
        if (cellValuesToDelete.size() == 1) {
            selectedValueToDelete = cellValuesToDelete.get(0);
            deleteCellContent();
        } else if (!cellValuesToDelete.isEmpty()) {
            //Tiene mas de 1 valor la celda, el usuario debe elegir que valor eliminar
            valuesToDeleteContainer.setVisible(true);
        }
    }

    private void deleteCellContent() {
        if (listener != null) {
            listener.deletedValue(selectedValueToDelete);
            hideInputUserView();
        }
    }

    private void initButtonsView() {
        int buttonPosX = firstButtonCoordinateX;
        int buttonPosY = firstButtonCoordinateY;
        for (int index = 0; index < this.allowedValuesToInput.size(); ++index) {
            JButton button = new JButton(this.allowedValuesToInput.get(index));
            button.setBounds(buttonPosX, buttonPosY,
                    buttonValuesWidth, buttonValuesWidth);
            add(button);
            if (rowIsCompleted(index)) {
                buttonPosY += spaceBetweenButtons + buttonValuesWidth;
                buttonPosX = firstButtonCoordinateX;
            } else {
                buttonPosX += (spaceBetweenButtons + buttonValuesWidth);
            }

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    textInput.setText(textInput.getText() + ((JButton) event.getSource()).getText());
                }
            });
        }
        createInteractiveButtons(buttonPosY);
    }

    private void createInteractiveButtons(int buttonPosY) {
        backspaceButton = new JButton("backspace");
        int backSpaceButtonPosX = firstButtonCoordinateX;
        int backSpaceButtonPosY = buttonPosY + buttonValuesWidth + 20;
        int backSpaceButtonWidth = buttonValuesWidth + 50;
        backspaceButton.setBounds(backSpaceButtonPosX, backSpaceButtonPosY,
                backSpaceButtonWidth, buttonValuesWidth);
        backspaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                textInput.setText(textInput.getText().substring(0, textInput.getText().length() - 1));
            }
        });
        add(backspaceButton);

        botonOk = new JButton("OK");
        botonOk.setBackground(Color.white);
        botonOk.setBounds(backSpaceButtonPosX + backSpaceButtonWidth + spaceBetweenButtons,
                backSpaceButtonPosY, buttonValuesWidth + 20, buttonValuesWidth);
        add(botonOk);
        deleteContentButton = new JButton("delete value");
        deleteContentButton.setBounds(backSpaceButtonPosX, backSpaceButtonPosY + buttonValuesWidth + spaceBetweenButtons,
                buttonValuesWidth * 4, buttonValuesWidth);
        deleteContentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                onClickDeleteCellContent();
            }
        });
        add(deleteContentButton);
    }

    private boolean rowIsCompleted(int index) {
        return ((index + 1) % buttonPerRow) == 0;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        textInput.setText("");
    }

    public void setListener(UserInputListener listener) {
        this.listener = listener;
    }

    private void inputTextIsReady() {
        String text = textInput.getText();
        setVisible(false);
        valuesToDeleteContainer.setVisible(false);
        if (listener != null && !text.isEmpty()) {
            listener.inputedText(text);
        }
    }

    /*@Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            inputTextIsReady();
        }
    }*/

    public interface UserInputListener {
        public void inputedText(String text);

        public void deletedValue(String text);
    }
}
