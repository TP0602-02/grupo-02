package ar.fiuba.tdd.template.board.cell;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Nicolas on 17/10/2016.
 */
public class ShowCellValuesView extends JFrame {

    private static ShowCellValuesView instance;

    public static final int buttonValuesWidth = 50;
    public static final int spaceBetweenButtons = 15;
    public static final int firstButtonCoordinateX = 10;
    public static final int firstButtonCoordinateY = 80;
    private static final int maxButtons = 4;

    private static int posXWindow = 300;
    private static int posYWindow = 300;
    private static int widthWindow = 350;
    private static int heightWindow = 300;
    private static final String TITLE_VALUES = "VALORES ACTUALES:";

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


    public static ShowCellValuesView getInstance() {
        if (instance == null) {
            instance = new ShowCellValuesView();
        }
        return instance;
    }

    public void showValues(ArrayList<String> values) {
        setVisible(true);
        hideAllButons();
        for (int button = 0; button < values.size(); ++button) {
            this.buttons.get(button).setText(values.get(button));
            this.buttons.get(button).setVisible(true);
        }
    }

    private void hideAllButons() {
        for (JButton button : this.buttons) {
            button.setVisible(false);
        }
    }

}
