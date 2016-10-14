package ar.fiuba.tdd.template.board.cell;

import ar.fiuba.tdd.template.board.cell.model.Cell;

import java.util.ArrayList;

/**
 * Created by Nicolas on 14/10/2016.
 */
public class RegionJson {

    private Cell leftTop;
    private Cell rightBottom;
    private ArrayList<Cell> exceptions;

    public RegionJson(Cell leftTop, Cell rightBottom, ArrayList<Cell> exceptions) {
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
        this.exceptions = exceptions;
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
}
