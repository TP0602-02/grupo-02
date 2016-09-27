package ar.fiuba.tdd.template.model;

public abstract class CellContent<T> {

    T value;

    public CellContent() {

    }

    public abstract T getValue();

    public abstract void setValue(T value);

}
