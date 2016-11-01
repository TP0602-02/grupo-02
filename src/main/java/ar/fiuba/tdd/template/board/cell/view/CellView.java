package ar.fiuba.tdd.template.board.cell.view;

import ar.fiuba.tdd.template.board.cell.model.SpecialTransoferValueImages;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Created by Nicolas on 27/9/2016.
 */
public class CellView extends JButton {

    private ClickCellListener listener;
    private String value;
    private ImageIcon iconRepresentation;

    public CellView() {
        //super("", SwingConstants.CENTER);
        setOpaque(true);
        iconRepresentation = null;
        setBackground(Color.white);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.black));

    }

    public CellView(String text) {
        this();
        initValue(text);
    }

    public String getValue() {
        return value;
    }

    private void initValue(String text) {
        this.value = text;
        this.iconRepresentation = SpecialTransoferValueImages.getInstance().getImageIconOf(text);
        setView();
    }

    private void setView() {
        if (iconRepresentation != null) {
            setIcon(iconRepresentation);
        } else {
            setText(value);
        }
    }

    public void setValues(ArrayList<String> values) {
        cleanCellView();
        for (String value : values) {
            setValue(value);
        }
    }

    public void setValue(String value) {
        cleanCellView();
        initValue(value);
    }

    private void cleanCellView() {
        removeAll();
        setText("");
        setIcon(null);
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
