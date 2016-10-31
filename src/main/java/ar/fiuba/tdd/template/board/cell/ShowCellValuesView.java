package ar.fiuba.tdd.template.board.cell;

import ar.fiuba.tdd.template.board.cell.model.CellContent;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;



/**
 * Created by Nicolas on 17/10/2016.
 */
public class ShowCellValuesView extends JFrame {

    public static final int buttonValuesWidth = 50;
    public static final int spaceBetweenButtons = 15;
    public static final int firstButtonCoordinateX = 10;
    public static final int firstButtonCoordinateY = 80;
    private static final int maxButtons = 12;
    private static final String TITLE_VALUES = "VALORES ACTUALES:";
    private static ShowCellValuesView instance;
    private static int posXWindow = 300;
    private static int posYWindow = 300;
    private static int widthWindow = 500;
    private static int heightWindow = 300;
    private ArrayList<JButton> buttons;

    private ShowCellValuesView() {
        this.buttons = new ArrayList<>();
        setLayout(null);
        setMinimumSize(new Dimension(widthWindow, heightWindow));
        setBounds(posXWindow, posYWindow, widthWindow, heightWindow);
        JLabel textInput = new JLabel(TITLE_VALUES);
        textInput.setFont(new Font("Serif", Font.PLAIN, 15));
        textInput.setHorizontalAlignment(JLabel.CENTER);
        textInput.setBounds(10, 10, widthWindow - 20, heightWindow / 3);
        add(textInput);
        prepareButtons();
    }

    public static ShowCellValuesView getInstance() {
        if (instance == null) {
            instance = new ShowCellValuesView();
        }
        return instance;
    }

    private void prepareButtons() {
        int posX = firstButtonCoordinateX;
        for (int index = 0; index < maxButtons; ++index) {
            JButton button = new JButton();
            button.setEnabled(false);
            button.setBounds(posX, firstButtonCoordinateY, buttonValuesWidth, buttonValuesWidth);
            posX += spaceBetweenButtons + buttonValuesWidth;
            add(button);
            this.buttons.add(button);
            button.setVisible(false);
        }
    }

    public void showValues(ArrayList<CellContent> values) {
        setVisible(true);
        hideAllButons();
        for (int content = 0; content < values.size(); ++content) {
            this.buttons.get(content).setText(values.get(content).getValue());
            this.buttons.get(content).setVisible(true);
            if (!values.get(content).isDeleteable()) {
                this.buttons.get(content).setBackground(Color.pink);
            }
        }
    }

    private void hideAllButons() {
        for (JButton button : this.buttons) {
            button.setBackground(Color.white);
            button.setVisible(false);
        }
    }

}
