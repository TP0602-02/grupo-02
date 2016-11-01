package ar.fiuba.tdd.template.board.cell.model;

import javax.swing.*;

/**
 * Created by Nicolas on 28/10/2016.
 */
public class SpecialTransoferValueImages {

    private static SpecialTransoferValueImages instance;

    private SpecialTransoferValueImages() {

    }

    public static SpecialTransoferValueImages getInstance() {
        if (instance == null) {
            instance = new SpecialTransoferValueImages();
        }
        return instance;
    }

    public ImageIcon getImageIconOf(String value) {
        if (value.equals("D")) {
            return new ImageIcon("src/down.png");
        } else if (value.equals("R")) {
            return new ImageIcon("src/right.png");
        } else if (value.equals("U")) {
            return new ImageIcon("src/up.png");
        } else if (value.equals("L")) {
            return new ImageIcon("src/left.png");
        } else {
            return null;
        }
    }
}
