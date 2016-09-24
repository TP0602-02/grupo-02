package ar.fiuba.tdd.template;

class ValueContent<T> extends CellContent<T> {

    ValueContent(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

}
