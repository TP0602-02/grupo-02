package ar.fiuba.tdd.template;

class ValueCell<T> extends CellContent<T> {

    ValueCell(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

}
