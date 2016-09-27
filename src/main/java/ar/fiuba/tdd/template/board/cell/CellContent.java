package ar.fiuba.tdd.template.board.cell;

public abstract class CellContent<T> implements Summable{

    protected T value;

    public CellContent() {

    }

    public abstract T getValue();

    public abstract void setValue(T value);

}
