package ar.fiuba.tdd.template.userinterface.view;


import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.board.cell.CellContent;
import ar.fiuba.tdd.template.board.cell.ValueContent;
import ar.fiuba.tdd.template.userinterface.controller.Facade;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * Created by Colo on 23/09/2016.
 */
public class Visualization extends JFrame {
    static final int textAndLabelSize = 40;
    static final int boardInitialPositionX = 200;
    static final int boardInitialPositionY = 300;
    static final int screenHeight = 768;
    static final int screenLenght = 1024;
    private final VisuMenu menu;
    private ArrayList<ArrayList<JLabel>> labelFields;
   // private JTextField[][] textFields;

    public Visualization() {
        this.getContentPane().setPreferredSize(new Dimension(screenLenght, screenHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.menu = new VisuMenu(screenLenght, screenHeight, this);
        menu.createMenu();
        this.add(menu);
        this.pack();
    }


    private void createBoard(String game) {
        int boardSize = Facade.getBoardSize();
       // textFields = new JTextField[boardSize][boardSize];
        initBoardDimensions(boardSize);
        this.addTitle(game);
        for (int counterX = 0; counterX < boardSize; counterX++) {
            for (int counterY = 0; counterY < boardSize; counterY++) {
                int positionX = counterX * textAndLabelSize + boardInitialPositionX;
                int positionY = counterY * textAndLabelSize + boardInitialPositionY;
                //addField(Facade.getInstance().getCellContent(counterX,counterY),counterX,counterY);
                //villereada para probar
                Random rand = new Random();
               // int x = rand.nextInt(5);
               /* if (x == 0) {
                    addField(new ClueContent(x = rand.nextInt(9)), positionX, positionY, counterX, counterY);
                } else {
                    if (x == 1) {
                        addField(new BlackContent(), positionX, positionY, counterX, counterY);
                    } else {
                        addField(new BlackContent(), positionX, positionY, counterX, counterY);
                    }
                }*/                    addField(new ValueContent(rand.nextInt(9)), positionX, positionY, counterX, counterY);

            }
        }
        JLabel label = new JLabel("", JLabel.CENTER);
        this.getContentPane().add(label);
    }

    private void initBoardDimensions(int boardSize) {
        labelFields = new ArrayList<>();
        for (int dimen = 0; dimen < boardSize; ++dimen) {
            ArrayList<JLabel> column = new ArrayList<JLabel>();
            labelFields.add(dimen,column);
            for (int row = 0; row < boardSize; ++row) {
                column.add(new JLabel());
            }
        }
    }

    private void addTitle(String game) {
        JLabel titleLabel = new JLabel(game);
        titleLabel.setBounds(boardInitialPositionX, boardInitialPositionY - 200, 300, 100);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 70));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleLabel);
    }
/*
    private void addField(BlankCell cell, int positionX, int positionY, int counterX, int counterY) {
        TextBox text = new TextBox();
        text.setBounds(positionX, yPosition, textAndLabelSize, textAndLabelSize);
        text.setHorizontalAlignment(JTextField.CENTER);
        getContentPane().add(text);
        textFields[counterX][counterY] = text;
    }

    private void addField(BlackCell cell, int positionX, int positionY, int counterX, int counterY) {
        JLabel blackLabel = new JLabel();
        blackLabel.setBounds(positionX, yPosition, textAndLabelSize, textAndLabelSize);
        blackLabel.setForeground(Color.black);
        blackLabel.setBackground(Color.black);
        blackLabel.setOpaque(true);
        getContentPane().add(blackLabel);
        labelFields[counterX][counterY] = blackLabel;
    }

    private void addField(ClueCell cell, int positionX, int positionY, int counterX, int counterY) {
        JLabel clueLabel = new JLabel(String.valueOf(cell.getValue()), SwingConstants.CENTER);
        clueLabel.setBounds(positionX, yPosition, textAndLabelSize, textAndLabelSize);
        getContentPane().add(clueLabel);
        labelFields[counterX][counterY] = clueLabel;
    }*/

    private void addField(CellContent cellContent, int positionX, int positionY, int counterX, int counterY) {
        Cell cell = new Cell(positionX,positionY);
        cell.setContent(cellContent);
        JLabel clueLabel = new JLabel(String.valueOf(cellContent.getValue()), SwingConstants.CENTER);
        clueLabel.setBounds(positionX, positionY, textAndLabelSize, textAndLabelSize);
        getContentPane().add(clueLabel);
       // labelFields.[counterX][counterY] = clueLabel;
        labelFields.get(counterX).get(counterY).setText(clueLabel.getText());
    }

    public void showVisu() {
        this.setVisible(true);
    }

    public void startGame(String game) {
        menu.setVisible(false);
        this.remove(menu);
        this.pack();
        Facade.getInstance().setGame(game);
        this.createBoard(game);
        this.pack();
    }
}
