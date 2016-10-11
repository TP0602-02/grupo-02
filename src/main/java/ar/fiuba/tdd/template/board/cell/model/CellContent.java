package ar.fiuba.tdd.template.board.cell.model;

public abstract class CellContent implements Summable, Editable {

    protected String value;

    public CellContent() {

    }

    public abstract String getValue();

    public abstract void setValue(String value);

    public abstract int getNumberValue();

}
