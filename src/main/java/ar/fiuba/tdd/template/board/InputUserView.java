package ar.fiuba.tdd.template.board;

import ar.fiuba.tdd.template.board.cell.view.CellView;
import ar.fiuba.tdd.template.drawers.DrawerFactory;
import ar.fiuba.tdd.template.userinterface.view.PuzzleView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;


/**
 * Created by Nicolas on 28/9/2016.
 */

public class InputUserView extends JFrame {

    public static final int buttonPerRow = 3;
    public static final int buttonValuesWidth = 50;
    public static final int spaceBetweenButtons = 15;
    public static final int firstButtonCoordinateX = 10;
    public static final int firstButtonCoordinateY = 80;
    public static final int inputUserViewWidth = 435;
    public static final int inputUserViewHeight = 600;
    private static InputUserView instance;
    JButton backspaceButton;
    JButton deleteContentButton;
    private JTextField textInput;
    private JButton botonOk;
    private UserInputListener listener;
    private ArrayList<String> allowedValuesToInput;
    private ArrayList<String> cellValuesToDelete;
    private JPanel valuesToDeleteContainer;
    private String selectedValueToDelete;


    private InputUserView(ArrayList<String> allowedValuesToInput) {
        this.allowedValuesToInput = allowedValuesToInput;
        this.selectedValueToDelete = "";
        setLayout(null);
        initTextInput();
        valuesToDeleteContainer = new JPanel();
        valuesToDeleteContainer.setLayout(null);
        valuesToDeleteContainer.setBounds(inputUserViewWidth / 2, 50, inputUserViewWidth / 2, inputUserViewHeight / 2);
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

    private void initTextInput() {
        JLabel label = new JLabel("Ingrese valor con teclado o presione los botones disponibles");
        label.setBounds(firstButtonCoordinateX, 1, inputUserViewWidth - 10, 30);
        label.setVisible(true);
        add(label);
        textInput = new JTextField();
        textInput.setEditable(true);
        textInput.setBounds(firstButtonCoordinateX, firstButtonCoordinateY - 50, 150, 20);
        add(textInput);
        initKeyListener();
    }

    private void initKeyListener() {
        textInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent event) {

            }

            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    inputTextIsReady();
                }
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent event) {

            }
        });
    }

    public static InputUserView createView(ArrayList<String> allowedValuesToInput) {
        instance = new InputUserView(allowedValuesToInput);
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
        int posY = 10;
        int height = 60;
        for (String value : cellValuesToDelete) {
            CellView button = new CellView();
            button.setBounds(10, posY, height, height);
            posY += 10 + height;
            DrawerFactory.getInstance().getDrawer().draw(button, value);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    selectedValueToDelete = button.getValue();
                    cleanAnySelectedValueToDelete();
                    button.setBackground(Color.GREEN);
                }
            });
            valuesToDeleteContainer.add(button);
        }
        createDeleteButton(posY);
    }

    private void createDeleteButton(int posY) {
        JButton buttonDeleteOk = new JButton("BORRAR!");
        buttonDeleteOk.setBounds(20, posY, 150, 30);
        buttonDeleteOk.setBackground(Color.white);
        buttonDeleteOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (!selectedValueToDelete.isEmpty()) {
                    deleteCellContent();
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un valor para eliminar.");
                }
                selectedValueToDelete = "";
            }
        });
        valuesToDeleteContainer.add(buttonDeleteOk);
    }

    private void cleanAnySelectedValueToDelete() {
        for (int i = 0; i < valuesToDeleteContainer.getComponentCount(); i++) {
            ((JButton) valuesToDeleteContainer.getComponents()[i]).setBackground(Color.white);
        }
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
            CellView button = new CellView();
            DrawerFactory.getInstance().getDrawer().draw(button, this.allowedValuesToInput.get(index));
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
                    textInput.setText(textInput.getText() + ((CellView) event.getSource()).getValue());
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
        textInput.requestFocus();
    }

    public void setListener(UserInputListener listener) {
        this.listener = listener;
    }

    private void inputTextIsReady() {
        String text = textInput.getText();
        setVisible(false);
        valuesToDeleteContainer.setVisible(false);
        if (listener != null && allowedValuesToInput.contains(text)) {
            listener.inputedText(text);
        }
    }


    public interface UserInputListener {
        public void inputedText(String text);

        public void deletedValue(String text);
    }
}
