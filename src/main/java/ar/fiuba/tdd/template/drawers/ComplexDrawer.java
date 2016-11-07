package ar.fiuba.tdd.template.drawers;

import ar.fiuba.tdd.template.board.cell.view.CellView;

import java.awt.*;
import javax.swing.*;

/**
 * Created by Nicolas on 3/11/2016.
 */
public class ComplexDrawer extends AbstractDrawer {

    private static final int NO_BORDER_PIXEL = 0;
    private static final int BORDER_PIXEL = 4;

    @Override
    protected void decorateCell(CellView cellViewToDraw, String valueToDraw) {
        cleanCellView();
        int left = paintBorder("L", valueToDraw);
        int right = paintBorder("R", valueToDraw);
        int up = paintBorder("U", valueToDraw);
        int down = paintBorder("D", valueToDraw);
        cellViewToDraw.setBorder(BorderFactory.createMatteBorder(up, left, down, right, Color.RED));
    }

    private int paintBorder(String border, String valueToDraw) {
        return valueToDraw.contains(border) ? BORDER_PIXEL : NO_BORDER_PIXEL;
    }

    @Override
    protected String getImagePackage() {
        return "";
    }
}
