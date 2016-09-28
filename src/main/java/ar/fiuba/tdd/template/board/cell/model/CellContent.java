package ar.fiuba.tdd.template.board.cell.model;

public abstract class CellContent<T> implements Summable,Editable{

    protected T value;

    public CellContent() {

    }

    public abstract T getValue();

    public abstract void setValue(T value);

}
