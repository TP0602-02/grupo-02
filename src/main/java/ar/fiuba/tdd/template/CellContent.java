package ar.fiuba.tdd.template;

abstract class CellContent<T> {

    T value;

    CellContent() {

    }

    abstract T getValue();

    abstract void setValue(T value);

}
