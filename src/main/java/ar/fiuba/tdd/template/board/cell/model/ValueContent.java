package ar.fiuba.tdd.template.board.cell.model;


public class ValueContent<T> extends CellContent<T> {

    public ValueContent(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean isSummable() {
        return true;
    }

    @Override
    public boolean isEditable() {
        return true;
    }
}
