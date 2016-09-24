package ar.fiuba.tdd.template.Model;

class BlackContent<T> extends CellContent<T> {

    public BlackContent() {
    }

    public T getValue() throws AssertionError {
        throw new AssertionError("Cannot get value");
    }

    public void setValue(T value) throws AssertionError {
        throw new AssertionError("Cannot set value");
    }

}
