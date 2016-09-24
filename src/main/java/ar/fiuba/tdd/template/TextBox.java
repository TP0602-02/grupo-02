package ar.fiuba.tdd.template;

import javax.swing.*;

/**
 * Created by Colo on 23/09/2016.
 */
public class TextBox extends JTextField {
    public TextBox() {

        this.addKeyListener(new TextBoxListener(this))
        ;
    }


}
