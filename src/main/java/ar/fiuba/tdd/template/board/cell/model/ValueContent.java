package ar.fiuba.tdd.template.board.cell.model;


import java.awt.*;

public class ValueContent extends CellContentWithValue {


    public ValueContent(String value) {
        super(value);
    }

    public ValueContent(int value) {
        super(value);
    }

    @Override
    public boolean isSummable() {
        return true;
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public Color getColorRepresentation() {
        return Color.WHITE;
    }
}
