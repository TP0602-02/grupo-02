package ar.fiuba.tdd.template;

class BlackContent<T> extends CellContent<T> {

    BlackContent() {
    }

    T getValue() throws AssertionError {
        throw new AssertionError("Cannot get value");
    }

    void setValue(T value) throws AssertionError {
        throw new AssertionError("Cannot set value");
    }

}
