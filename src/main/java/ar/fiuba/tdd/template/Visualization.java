package ar.fiuba.tdd.template;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
    private JLabel[][] labelFields;
    private JTextField[][] textFields;

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
        textFields = new JTextField[boardSize][boardSize];
        labelFields = new JLabel[boardSize][boardSize];
        this.addTitle(game);
        for (int counterX = 0; counterX < boardSize; counterX++) {
            for (int counterY = 0; counterY < boardSize; counterY++) {
                int xPosition = counterX * textAndLabelSize + boardInitialPositionX;
                int yPosition = counterY * textAndLabelSize + boardInitialPositionY;
                //addField(Facade.getInstance().getCellContent(counterX,counterY),counterX,counterY);
                //villereada para probar
                Random rand = new Random();
                int x = rand.nextInt(5);
                if (x == 0) {
                    addField(new ClueCell(x = rand.nextInt(9)), xPosition, yPosition, counterX, counterY);
                } else {
                    if (x == 1) {
                        addField(new BlackCell(), xPosition, yPosition, counterX, counterY);
                    } else {
                        addField(new BlankCell(), xPosition, yPosition, counterX, counterY);
                    }
                }
            }
        }
        JLabel label = new JLabel("", JLabel.CENTER);
        this.getContentPane().add(label);
    }

    private void addTitle(String game) {
        JLabel titleLabel = new JLabel(game);
        titleLabel.setBounds(boardInitialPositionX, boardInitialPositionY - 200, 300, 100);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 70));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleLabel);
    }

    private void addField(BlankCell cell, int xPosition, int yPosition, int counterX, int counterY) {
        TextBox text = new TextBox();
        text.setBounds(xPosition, yPosition, textAndLabelSize, textAndLabelSize);
        text.setHorizontalAlignment(JTextField.CENTER);
        getContentPane().add(text);
        textFields[counterX][counterY] = text;
    }

    private void addField(BlackCell cell, int xPosition, int yPosition, int counterX, int counterY) {
        JLabel blackLabel = new JLabel();
        blackLabel.setBounds(xPosition, yPosition, textAndLabelSize, textAndLabelSize);
        blackLabel.setForeground(Color.black);
        blackLabel.setBackground(Color.black);
        blackLabel.setOpaque(true);
        getContentPane().add(blackLabel);
        labelFields[counterX][counterY] = blackLabel;
    }

    private void addField(ClueCell cell, int xPosition, int yPosition, int counterX, int counterY) {
        JLabel clueLabel = new JLabel(String.valueOf(cell.getValue()), SwingConstants.CENTER);
        clueLabel.setBounds(xPosition, yPosition, textAndLabelSize, textAndLabelSize);
        getContentPane().add(clueLabel);
        labelFields[counterX][counterY] = clueLabel;
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
