package ar.fiuba.tdd.template.userinterface.view;

import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * Created by Colo on 31/10/2016.
 */
public class Undo {
    private static JButton undoButtom;

    public static JButton getUndoButtom() {
        undoButtom = new JButton("DESHACER JUGADA");
        return undoButtom;
    }

    public static void setActionListener(ActionListener listener) {
        undoButtom.addActionListener(listener);
    }

}