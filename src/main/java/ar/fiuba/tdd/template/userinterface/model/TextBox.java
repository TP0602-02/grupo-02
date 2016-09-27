package ar.fiuba.tdd.template.userinterface.model;


import ar.fiuba.tdd.template.userinterface.controller.TextBoxListener;

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
