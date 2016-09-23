package ar.fiuba.tdd.template.Model;


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

    @Override
    void setChangeable(Cell cell) {
        cell.setChangeable(false);
    }
}
