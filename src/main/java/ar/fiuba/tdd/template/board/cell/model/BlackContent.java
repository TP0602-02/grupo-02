package ar.fiuba.tdd.template.board.cell.model;

public class BlackContent<T> extends CellContent<T> {

    public void setDef(DefValue<T> def) {
        this.def = def;
    }

    private DefValue<T> def;

    public BlackContent() {
    }

    public BlackContent(DefValue defValue) {
        this.def = defValue;
    }

    public T getValue() throws AssertionError {
        // throw new AssertionError("Cannot get value");
        return def.getDefValue();

    }

    public void setValue(T value) throws AssertionError {
        //throw new AssertionError("Cannot set value");
    }

    @Override
    public boolean isSummable() {
        return false;
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    public interface DefValue<T> {
        public T getDefValue();
    }
}
