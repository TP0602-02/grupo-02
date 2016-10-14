package ar.fiuba.tdd.template.board.cell.model;

import ar.fiuba.tdd.template.entity.Coordinate;

/**
 * Created by Nicolas on 7/10/2016.
 */
public class CellSingleValue extends Cell {

    public CellSingleValue(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void setContent(CellContent newContentCell) {
        contents.clear();
        contents.add(newContentCell);
    }
}
