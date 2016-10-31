package ar.fiuba.tdd.template.userinterface.view;

import java.awt.*;
import javax.swing.*;



/**
 * Created by Nicolas on 17/10/2016.
 */
public class WinGameView extends JFrame {

    private static final String WIN_GAME = "FELICITACIONES HAS GANADO!!!";
    private static int posXWindow = 300;
    private static int posYWindow = 300;
    private static int widthWindow = 350;
    private static int heightWindow = 300;

    public WinGameView() {
        setMinimumSize(new Dimension(widthWindow, heightWindow));
        setLayout(null);
        JLabel textInput = new JLabel(WIN_GAME);
        setBounds(posXWindow, posYWindow, widthWindow, heightWindow);
        textInput.setFont(new Font("Serif", Font.PLAIN, 15));
        textInput.setBounds(10, posYWindow / 2, widthWindow - 20, heightWindow / 3);
        add(textInput);
    }

}
