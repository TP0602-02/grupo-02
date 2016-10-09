package ar.fiuba.tdd.template.board.cell.model;

/**
 * Created by matiaskamien on 07/10/16.
 */
public class GenericValue {

    private char value;

    public GenericValue(char value) {
        this.value = value;
    }

    public char getValueAsChar() {
        return this.value;
    }

    public int getValueAsInt() {
        return Character.getNumericValue(this.value);
    }
}
