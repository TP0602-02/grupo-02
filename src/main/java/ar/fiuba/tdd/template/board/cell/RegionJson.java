package ar.fiuba.tdd.template.board.cell;

import ar.fiuba.tdd.template.board.cell.model.Cell;

import java.util.ArrayList;

public class RegionJson {
    private Cell leftTop;
    private Cell rightBottom;
    private ArrayList<Cell> exceptions;
    private int total;

    public RegionJson(Cell leftTop, Cell rightBottom, ArrayList<Cell> exceptions, int total) {
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
        this.exceptions = exceptions;
        this.total = total;
    }

    public Cell getLeftTop() {
        return leftTop;
    }

    public Cell getRightBottom() {
        return rightBottom;
    }

    public ArrayList<Cell> getExceptions() {
        return exceptions;
    }

    public int getTotal() {
        return total;
    }

}
