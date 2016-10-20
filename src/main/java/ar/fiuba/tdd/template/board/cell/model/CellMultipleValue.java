package ar.fiuba.tdd.template.board.cell.model;

import ar.fiuba.tdd.template.entity.Coordinate;

/**
 * Created by Nicolas on 7/10/2016.
 */
public class CellMultipleValue extends Cell {

    public CellMultipleValue(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void setContent(CellContent newContentCell) {
        if(!hasValue (newContentCell.getNumberValue())){
            contents.add(newContentCell);
        }
    }

}
