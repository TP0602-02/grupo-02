package ar.fiuba.tdd.template.board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * Created by Nicolas on 28/9/2016.
 */

public class InputUserView extends JFrame implements ActionListener {
    private JTextField textInput;
    private JLabel label;
    private JButton botonOk;
    private UserInputListener listener;

    public InputUserView() {
        setLayout(null);
        label = new JLabel("Ingrese valor: ");
        label.setBounds(10, 10, 100, 30);
        add(label);
        textInput = new JTextField();
        textInput.setBounds(120, 10, 150, 20);
        add(textInput);
        botonOk = new JButton("OK");
        botonOk.setBounds(10, 80, 100, 30);
        add(botonOk);
        botonOk.addActionListener(this);
    }

    public void setListener(UserInputListener listener) {
        this.listener = listener;
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonOk) {
            String text = textInput.getText();
            setVisible(false);
            if (listener != null) {
                listener.textInputed(text);
            }
        }
    }

    public interface UserInputListener {
        public void textInputed(String text);
    }
}
