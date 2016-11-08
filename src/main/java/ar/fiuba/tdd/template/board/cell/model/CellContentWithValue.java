package ar.fiuba.tdd.template.board.cell.model;

import ar.fiuba.tdd.template.entity.SpecialCharactersParser;

public abstract class CellContentWithValue extends CellContent {

    public CellContentWithValue(String value) {
        setValue(value);
    }

    public CellContentWithValue(int value) {
        setValue(String.valueOf(value));
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getNumberValue() {
        return SpecialCharactersParser.getInstance().getValueOf(this.value);
    }

}
