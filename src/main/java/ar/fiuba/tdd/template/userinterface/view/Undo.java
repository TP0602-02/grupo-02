package ar.fiuba.tdd.template.userinterface.view;

import ar.fiuba.tdd.template.puzzle.PuzzleController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * Created by Colo on 31/10/2016.
 */
public class Undo {
    private static JButton undoButtom;

    public static JButton getUndoButtom() {
        if (undoButtom == null) {
            undoButtom = new JButton("DESHACER JUGADA");
        }
        return undoButtom;
    }

    public static void config(PuzzleController controller) {
        JButton button = getUndoButtom();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controller.undoPlay();
            }
        });
    }
}
