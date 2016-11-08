package ar.fiuba.tdd.template.drawers;

import ar.fiuba.tdd.template.board.cell.view.CellView;
import ar.fiuba.tdd.template.entity.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;


/**
 * Created by Nicolas on 2/11/2016.
 */
public abstract class AbstractDrawer {

    protected CellView cellViewToDraw;
    private static final String BARRA = "barra";
    private static final String CONTRABARRA = "contrabarra";

    public void draw(CellView cellToDraw, String valueToDraw) {
        this.cellViewToDraw = cellToDraw;
        cleanCellView();
        this.cellViewToDraw.setValue(valueToDraw);
        setView(getImageOf(valueToDraw), valueToDraw);
        decorateCell(this.cellViewToDraw, valueToDraw);

    }

    public void draw(CellView cellToDraw, ArrayList<String> multipleValues) {
        StringBuffer valueJoined = new StringBuffer();
        for (String value : multipleValues) {
            valueJoined.append(value);
        }
        //alphabetical sort --> example: "U" = UP and "R" = "RIGHT" --> FINAL STRING = "RU"
        char[] chars = valueJoined.toString().toCharArray();
        Arrays.sort(chars);
        draw(cellToDraw, new String(chars));
    }

    private ImageIcon getImageOf(String valueToDraw) {
        valueToDraw = isSpecialValue(valueToDraw);
        ImageIcon imageIcon = new ImageIcon(getImagePackage() + valueToDraw + ".png");
        return (imageIcon.getImageLoadStatus() == MediaTracker.COMPLETE) ? imageIcon : null;
    }

    private String isSpecialValue(String valueToDraw) {
        String value = valueToDraw;
        if (valueToDraw.equals(Constants.UP_DIAGONAL)) {
            value = BARRA;
        } else if (valueToDraw.equals(Constants.DOWN_DIAGONAL)) {
            value = CONTRABARRA;
        }
        return value;
    }

    protected void cleanCellView() {
        this.cellViewToDraw.removeAll();
        this.cellViewToDraw.setText("");
        this.cellViewToDraw.setIcon(null);
    }

    /**
     * To decorate the cellView.
     *
     * @param cellViewToDraw cell to decorate.
     */
    protected abstract void decorateCell(CellView cellViewToDraw, String valueToDraw);

    protected abstract String getImagePackage();

    protected void setView(ImageIcon imageIcon, String value) {
        if (imageIcon != null) {
            this.cellViewToDraw.setIcon(imageIcon);
        } else {
            this.cellViewToDraw.setText(value);
        }
    }


}
