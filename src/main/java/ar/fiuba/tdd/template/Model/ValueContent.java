package ar.fiuba.tdd.template.Model;

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

}
