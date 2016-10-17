package ar.fiuba.tdd.template.board.cell.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
        setText(text);
    }

    public void setValues(ArrayList<String> values) {
        //TODO VER LA FORMA DE DIBUJAR VARIOS VALORES EN UNA CELL VIEW
        removeAll();
        setText("");
        for (String value : values) {
            //TODO no sera un simple setText caundo haya que dibujar varios valores en la misma celda
            setText(value);
        }
    }

    public interface ClickCellListener {
        public void onClickForWrite();

        public void onClickForRead();
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
                    if (SwingUtilities.isLeftMouseButton(evemt)) {
                        listener.onClickForWrite();
                    } else if (SwingUtilities.isRightMouseButton(evemt)) {
                        listener.onClickForRead();
                    }
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
