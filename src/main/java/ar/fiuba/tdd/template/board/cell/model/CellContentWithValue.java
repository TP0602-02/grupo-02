package ar.fiuba.tdd.template.board.cell.model;

import ar.fiuba.tdd.template.entity.SpecialCharactersParser;

/**
 * Created by Nicolas on 9/10/2016.
 */
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

    public int getNumberValue() {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return getNumberValueOfSpecialString();
        }
    }

    public void setValue(String value) {
        this.value = value;
    }

    private int getNumberValueOfSpecialString() {
        return SpecialCharactersParser.getInstance().getValueOf(this.value);

    }
}
