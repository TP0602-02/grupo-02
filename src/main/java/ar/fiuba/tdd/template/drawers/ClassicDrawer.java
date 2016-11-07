package ar.fiuba.tdd.template.drawers;

import ar.fiuba.tdd.template.board.cell.view.CellView;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;


/**
 * Created by Nicolas on 3/11/2016.
 */
public class ClassicDrawer extends AbstractDrawer {

    @Override
    protected void decorateCell(CellView cellViewToDraw, String valueToDraw) {
        cellViewToDraw.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.black));

    }

    @Override
    protected String getImagePackage() {
        return "src/images/no-border-images/";
    }
}
