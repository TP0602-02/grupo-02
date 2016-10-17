package ar.fiuba.tdd.template.userinterface.view;

import java.awt.*;
import javax.swing.*;

/**
 * Created by Nicolas on 17/10/2016.
 */
public class WinGameView extends JFrame {

    private static int posXWindow = 300;
    private static int posYWindow = 300;
    private static int widthWindow = 350;
    private static int heightWindow = 300;
    private static final String WIN_GAME = "FELICITACIONES HAS GANADO!!!";

    public WinGameView() {
        setLayout(null);
        setMinimumSize(new Dimension(widthWindow, heightWindow));
        setBounds(posXWindow, posYWindow, widthWindow, heightWindow);
        JLabel textInput = new JLabel(WIN_GAME);
        textInput.setFont(new Font("Serif", Font.PLAIN, 15));
        textInput.setHorizontalAlignment(JLabel.CENTER);
        textInput.setBounds(10, posYWindow / 2, widthWindow - 20, heightWindow / 3);
        add(textInput);
    }

}
