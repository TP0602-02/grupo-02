package ar.fiuba.tdd.template.board.cell;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;

import java.util.ArrayList;

public class RegionJson {
    private Cell leftTop;
    private Cell rightBottom;
    private ArrayList<Cell> exceptions;
    private CellContent cellContent;
    private boolean graficable;


    public RegionJson(Cell leftTop, Cell rightBottom, ArrayList<Cell> exceptions, boolean graficable) {
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
        this.exceptions = exceptions;
        this.graficable = graficable;
    }

    public boolean isGraficable() {
        return graficable;
    }

    public void setCellContent(CellContent cellContent) {
        this.cellContent = cellContent;
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

    public CellContent getCellContent() {
        return cellContent;
    }

}
