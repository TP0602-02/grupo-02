package ar.fiuba.tdd.template;


class ClueCell<T> extends CellContent<T> {

    private T value;

    ClueCell(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

}
