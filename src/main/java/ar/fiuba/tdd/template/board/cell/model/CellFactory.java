package ar.fiuba.tdd.template.board.cell.model;

import java.util.ArrayList;

/**
 * Created by Nicolas on 7/10/2016.
 */
public class CellFactory {

    public static final String CELL_SINGLE_VALUE = "CELL_SINGLE";

    //public static final String CELL_MULTIPLEE_VALUE = "CELL_MULTIPLE";
    public Cell createCell(String cellType, int x, int y) {
        //Por defecto si no se especifica bien el cellType retorna una CellMultipleValue
        if (cellType.equals(CELL_SINGLE_VALUE)) {
            return new CellSingleValue(x, y);
        }
        return new CellMultipleValue(x, y);
    }
}
