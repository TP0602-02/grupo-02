package ar.fiuba.tdd.template;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Colo on 23/09/2016.
 */
public class Visualization extends JFrame {
    private final Facade facade;
    static final int textAndLabelSize=40;
    static final int boardInitialPositionX=200;
    static final int boardInitialPositionY=100;
    private JLabel[][] labelFields;
    private JTextField[][] textFields;
    public Visualization(Facade facade){
        this.facade=facade;
        this.getContentPane().setPreferredSize(new Dimension(1024,768));
        createBoard();
        JLabel label =new JLabel("",JLabel.CENTER);
        this.getContentPane().add(label);
        this.pack();
    }

    private void createBoard() {
        int boardSize=Facade.getBoardSize();
        textFields=new JTextField[boardSize][boardSize];
        labelFields=new JLabel[boardSize][boardSize];
        for(int counterX=0;counterX<boardSize;counterX++){
            for(int counterY=0;counterY<boardSize;counterY++){
                //addField(facade.getCellContent(counterX,counterY),counterX,counterY);
                //villereada para probar
                Random rand=new Random();
                int x=rand.nextInt(5);
                if(x==0){
                    addField(new ClueCell(x=rand.nextInt(9)),counterX,counterY);
                }else{
                    if(x==1){
                        addField(new BlackCell(),counterX,counterY);
                    }else{
                        addField(new BlankCell(),counterX,counterY);
                    }
                }

            }
        }
    }

    private void addField(BlankCell cell,int counterX,int counterY) {
        TextBox  text = new TextBox ();
        int xPosition=counterX*textAndLabelSize+boardInitialPositionX;
        int yPosition=counterY*textAndLabelSize+boardInitialPositionY;
        text.setBounds(xPosition,yPosition,textAndLabelSize,textAndLabelSize);
        text.setHorizontalAlignment(JTextField.CENTER);
        getContentPane().add(text);
        textFields[counterX][counterY]=text;
    }
    private void addField(BlackCell cell,int counterX,int counterY) {
        JLabel blackLabel = new JLabel();
        int xPosition=counterX*textAndLabelSize+boardInitialPositionX;
        int yPosition=counterY*textAndLabelSize+boardInitialPositionY;
        blackLabel.setBounds(xPosition,yPosition,textAndLabelSize,textAndLabelSize);
        blackLabel.setForeground(Color.black);
        blackLabel.setBackground(Color.black);
        blackLabel.setOpaque(true);
        getContentPane().add(blackLabel);
        labelFields[counterX][counterY]=blackLabel;
    }
    private void addField(ClueCell cell,int counterX,int counterY) {
        JLabel clueLabel = new JLabel(String.valueOf(cell.getValue()),SwingConstants.CENTER);
        int xPosition=counterX*textAndLabelSize+boardInitialPositionX;
        int yPosition=counterY*textAndLabelSize+boardInitialPositionY;
        clueLabel.setBounds(xPosition,yPosition,textAndLabelSize,textAndLabelSize);
        getContentPane().add(clueLabel);
        labelFields[counterX][counterY]=clueLabel;
    }

    public void showVisu() {
        this.setVisible(true);
    }
}
