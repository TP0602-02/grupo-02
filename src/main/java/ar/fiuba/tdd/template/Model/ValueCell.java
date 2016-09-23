package ar.fiuba.tdd.template.Model;

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

    @Override
    void setChangeable(Cell cell) {
        cell.setChangeable(true);
    }
}
