package ar.fiuba.tdd.template.board.cell.model;

import ar.fiuba.tdd.template.entity.Coordinate;

/**
 * Created by Nicolas on 7/10/2016.
 */
public class CellFactory {

    public static final String CELL_SINGLE_VALUE = "CELL_SINGLE";

    public Cell createCell(String cellType, Coordinate coordinate) {
        //Por defecto si no se especifica bien el cellType retorna una CellMultipleValue
        if (cellType.equals(CELL_SINGLE_VALUE)) {
            return new CellSingleValue(coordinate);
        }
        return new CellMultipleValue(coordinate);

    }


}
