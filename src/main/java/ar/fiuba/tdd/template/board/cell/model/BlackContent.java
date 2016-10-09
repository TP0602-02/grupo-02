package ar.fiuba.tdd.template.board.cell.model;

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
        return 0;
    }

    @Override
    public void setValue(String value) {

    }

    @Override
    public boolean isSummable() {
        return false;
    }

    @Override
    public boolean isEditable() {
        return false;
    }
/*
    public interface DefValue<T> {
        public T getDefValue();
    }*/
}
