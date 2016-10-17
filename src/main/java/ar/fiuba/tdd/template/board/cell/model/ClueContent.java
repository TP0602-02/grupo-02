package ar.fiuba.tdd.template.board.cell.model;


import java.awt.*;

public class ClueContent extends CellContentWithValue {


    public ClueContent(String value) {
        super(value);
    }

    public ClueContent(int value) {
        super(value);
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
    public Color getColorRepresentation() {
        return Color.RED;
    }
}
