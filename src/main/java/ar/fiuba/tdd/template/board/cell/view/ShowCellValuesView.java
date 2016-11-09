package ar.fiuba.tdd.template.board.cell.view;

import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.drawers.DrawerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Created by Nicolas on 17/10/2016.
 */
public class ShowCellValuesView extends JFrame {

    private static ShowCellValuesView instance;

    public static final int buttonValuesWidth = 50;
    public static final int spaceBetweenButtons = 15;
    public static final int firstButtonCoordinateX = 10;
    public static final int firstButtonCoordinateY = 80;
    private static final int maxButtons = 12;

    private static int posXWindow = 300;
    private static int posYWindow = 300;
    private static int widthWindow = 500;
    private static int heightWindow = 300;
    private static final String TITLE_VALUES = "VALORES ACTUALES:";

    private ArrayList<CellView> buttons;

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
            CellView button = new CellView();
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

    public void showValues(List<CellContent> values) {
        setVisible(true);
        hideAllButons();
        for (int content = 0; content < values.size(); ++content) {
            DrawerFactory.getInstance().getDrawer().draw(this.buttons.get(content),
                    values.get(content).getValue());
            this.buttons.get(content).setVisible(true);
            if (!values.get(content).isDeleteable()) {
                this.buttons.get(content).setBackground(values.get(content).getColorRepresentation());
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
