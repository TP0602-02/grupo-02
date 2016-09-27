package ar.fiuba.tdd.template.board.cell;


public class ClueContent<T> extends CellContent<T> {

    private T value;

    public ClueContent(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean isSummable() {
        return false;
    }
}