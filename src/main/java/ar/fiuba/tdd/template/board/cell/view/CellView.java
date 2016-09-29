package ar.fiuba.tdd.template.board.cell.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Created by Nicolas on 27/9/2016.
 */
public class CellView extends JLabel {

    private ClickCellListener listener;

    public CellView() {
        super("", SwingConstants.CENTER);
        setOpaque(true);
        setBackground(Color.white);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.black));
    }

    public CellView(String text) {
        this();
        if (text.equals("black")) {
            setBackground(Color.BLACK);
        } else {
            setText(text);
        }
    }

    public interface ClickCellListener {
        public void onClick();
    }


    public void setListener(ClickCellListener listener) {
        this.listener = listener;
        initListener();
    }

    private void initListener() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent evemt) {
                if (listener != null) {
                    listener.onClick();
                }
            }

            @Override
            public void mousePressed(MouseEvent evemt) {
            }

            @Override
            public void mouseReleased(MouseEvent evemt) {
            }

            @Override
            public void mouseEntered(MouseEvent evemt) {
            }

            @Override
            public void mouseExited(MouseEvent evemt) {
            }
        });
    }
}
