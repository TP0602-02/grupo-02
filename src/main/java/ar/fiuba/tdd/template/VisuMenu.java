package ar.fiuba.tdd.template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by Colo on 25/09/2016.
 */
public class VisuMenu extends Container {
    static final int buttonHeight = 50;
    static final int buttonLenght = 200;
    static final int menuInitialPositionX = 400;
    static final int menuInitialPositionY = 100;
    static final int distaneButtons = 25;
    private Visualization frame;
    private JButton[] buttons;

    public VisuMenu(int screenLenght, int screenHeight, Visualization frame) {
        this.setPreferredSize(new Dimension(screenLenght, screenHeight));
        this.frame = frame;
    }


    public void createMenu() {
        LinkedList<String> games = Facade.getInstance().getGames();
        int gameQuantity = games.size();
        buttons = new JButton[gameQuantity];
        JLabel titleLabel = new JLabel("Juegos");
        titleLabel.setBounds(menuInitialPositionX, menuInitialPositionY, 200, 100);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 70));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleLabel);
        TheHandler handler = new TheHandler(frame);
        for (int counter = 0; counter < gameQuantity; counter++) {
            int yPosition = (counter + 1) * buttonHeight + 2 * menuInitialPositionY + distaneButtons;
            JButton button = createButton(games.get(counter), menuInitialPositionX, yPosition, handler);
            buttons[counter] = button;
        }
        this.add(new JLabel());
    }

    private JButton createButton(String s, int xPosition, int yPosition, TheHandler handler) {
        JButton button = new JButton(s);
        button.setBounds(xPosition, yPosition, buttonLenght, buttonHeight);
        button.addActionListener(handler);
        //button.setHorizontalAlignment(JButton.CENTER);
        this.add(button);
        return button;
    }

    private class TheHandler implements ActionListener {
        private final Visualization frame;

        public TheHandler(Visualization frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent event) {
            LinkedList<String> games = Facade.getInstance().getGames();
            int gameQuantity = games.size();
            for (int counter = 0; counter < gameQuantity; counter++) {
                JButton button = buttons[counter];
                if (event.getSource() == button) {
                    frame.startGame(button.getText());
                }
            }
        }
    }


}
