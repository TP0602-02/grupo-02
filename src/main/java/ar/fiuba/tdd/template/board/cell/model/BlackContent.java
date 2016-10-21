package ar.fiuba.tdd.template.board.cell.model;

import java.awt.*;

public class BlackContent extends CellContent {

    public BlackContent() {
    }

    @Override
    public String getValue() {
        return "";
    }

    @Override
    public int getNumberValue() {
        return -1;
    }

    @Override
    public void setValue(String value) {

    }

    @Override
    public Color getColorRepresentation() {
        return Color.BLACK;
    }

    @Override
    public boolean isSummable() {
        return false;
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public boolean isShowableInBoard() {
        return true;
    }

    @Override
    public boolean isDeleteable() {
        return false;
    }

}
