package ar.fiuba.tdd.template.board.cell.model;

import java.awt.*;

public abstract class CellContent implements Summable, Editable, ShowableInBoard, Deleteable {

    protected String value;

    public CellContent() {

    }

    public abstract String getValue();

    public abstract void setValue(String value);

    public abstract int getNumberValue();

    public abstract Color getColorRepresentation();

}
