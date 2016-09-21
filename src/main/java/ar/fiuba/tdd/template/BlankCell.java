package ar.fiuba.tdd.template;

class BlankCell<T> extends CellContent<T> {

    BlankCell() {
    }

    T getValue() throws AssertionError {
        throw new AssertionError("Cannot get value");
    }

    void setValue(T value) throws AssertionError {
        throw new AssertionError("Cannot set value");
    }

}
