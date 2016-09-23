package ar.fiuba.tdd.template.Model;

abstract class CellContent<T> {

    T value;

    CellContent() {

    }

    abstract T getValue();

    abstract void setValue(T value);

    abstract void setChangeable(Cell cell);

}
