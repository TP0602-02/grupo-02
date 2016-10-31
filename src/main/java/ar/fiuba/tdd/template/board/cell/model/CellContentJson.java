package ar.fiuba.tdd.template.board.cell.model;

/**
 * Created by Nicolas on 19/10/2016.
 */
public class CellContentJson {
    private int id;
    private int value;

    public CellContentJson(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }


}
