package ar.fiuba.tdd.template.board.cell.model;

import java.awt.*;

public class BlackContent extends CellContent {

  /*  public void setDef(DefValue<T> def) {
        this.def = def;
    }*/

    // private DefValue<T> def;

    public BlackContent() {
    }

    @Override
    public String getValue() {
        return "";
    }

   /* public BlackContent(DefValue defValue) {
        this.def = defValue;
    }*/

   /* public T getValue() throws AssertionError {
        // throw new AssertionError("Cannot get value");
        return def.getDefValue();

    }*/

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
/*
    public interface DefValue<T> {
        public T getDefValue();
    }*/
}
