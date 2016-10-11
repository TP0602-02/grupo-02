package ar.fiuba.tdd.template.board.cell.view;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 * Created by Nicolas on 27/9/2016.
 */
public class CellView extends JLabel {

    private ClickCellListener listener;
    private int leftBorder;
    private int rightBorder;
    private int topBorder;
    private int bottomBorder;

    public CellView() {
        super("", SwingConstants.CENTER);
        leftBorder = rightBorder = topBorder = bottomBorder = 1;
        setOpaque(true);
        setBackground(Color.white);
        //setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.black));
        //setBorder(new MatteBorder(0,0,1,1,Color.BLACK));//Para los bordes se pueden confirgurar


    }

    public CellView(String text) {
        this();
        if (text.equals("black")) {
            setBackground(Color.BLACK);
        } else {
            setText(text);
        }
    }

    public void borderSetter(int xmove, int ymove, boolean borderTrue) {
        if (!borderTrue) {
            if (xmove == 1 && ymove == 0) {
                rightBorder += 2;
            } else if (xmove == -1 && ymove == 0) {
                leftBorder += 2;
            } else if (xmove == 0 && ymove == 1) {
                bottomBorder += 2;
            } else if (xmove == 0 && ymove == -1) {
                topBorder += 2;
            }

        }
    }

    public void refreshBorders() {
        setBorder(new MatteBorder(topBorder, leftBorder, bottomBorder, rightBorder, Color.BLACK));//Para los bordes se pueden confirgurar


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

    public interface ClickCellListener {
        public void onClick();
    }
}
