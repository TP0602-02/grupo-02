package ar.fiuba.tdd.template.userinterface.view;

import ar.fiuba.tdd.template.userinterface.controller.Facade;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

/**
 * Created by Colo on 25/09/2016.
 */
public class HomeView extends Container {
    static final int buttonHeight = 50;
    static final int buttonLenght = 200;
    static final int menuInitialPositionX = 400;
    static final int menuInitialPositionY = 100;
    static final int distaneButtons = 25;
    private PuzzleView frame;
    private JButton[] buttons;

    public HomeView(int screenLenght, int screenHeight, PuzzleView frame) {
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
            int positionY = (counter + 1) * buttonHeight + 2 * menuInitialPositionY + distaneButtons;
            JButton button = createButton(games.get(counter), menuInitialPositionX, positionY, handler);
            buttons[counter] = button;
        }
        this.add(new JLabel());
    }

    private JButton createButton(String gameName, int positionX, int positionY, TheHandler handler) {
        JButton button = new JButton(gameName);
        button.setBounds(positionX, positionY, buttonLenght, buttonHeight);
        button.addActionListener(handler);
        //button.setHorizontalAlignment(JButton.CENTER);
        this.add(button);
        return button;
    }

    private class TheHandler implements ActionListener {
        private final PuzzleView frame;

        public TheHandler(PuzzleView frame) {
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
