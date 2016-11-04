package ar.fiuba.tdd.template.board.cell.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Created by Nicolas on 27/9/2016.
 */
public class CellView extends JButton {

    private ClickCellListener listener;
    private String value;

    public CellView() {
        setOpaque(true);
        setBackground(Color.white);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.black));

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
