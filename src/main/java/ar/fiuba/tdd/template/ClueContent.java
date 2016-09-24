package ar.fiuba.tdd.template;


class ClueContent<T> extends CellContent<T> {

    private T value;

    ClueContent(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

}
