package ar.fiuba.tdd.template.entity;

/**
 * Created by Nicolas on 14/10/2016.
 */
public class Coordinate {


    private int row;
    private int column;

    @SuppressWarnings("CPD-START")

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    @SuppressWarnings("CPD-END")

    public int getRow() {
        return row;
    }



}
