package ar.fiuba.tdd.template;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Colo on 23/09/2016.
 */
public class TextBoxListener implements KeyListener {

    private final TextBox cajaTexto;

    public TextBoxListener(TextBox cajaTexto) {
        this.cajaTexto = cajaTexto;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!(e.getKeyChar() == 27 || e.getKeyChar() == 65535))//Cuando se cambie un valor va a saltar
        {
            System.out.println("Controller Esta comprobando el movimiento si es valido");
            try {
                Facade.getInstance().comprobarJugada();
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Movimiento no valido");
                cajaTexto.setText("");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
