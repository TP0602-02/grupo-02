package ar.fiuba.tdd.template.board.cell.model;

import java.util.ArrayList;

/**
 * Created by Nicolas on 7/10/2016.
 */
public class CellFactory {

    public static final String CELL_SINGLE_VALUE = "CELL_SINGLE";

    //public static final String CELL_MULTIPLEE_VALUE = "CELL_MULTIPLE";
    public Cell createCell(String cellType, int posX, int posY) {
        //Por defecto si no se especifica bien el cellType retorna una CellMultipleValue
        if (cellType.equals(CELL_SINGLE_VALUE)) {
            return new CellSingleValue(posX, posY);
        }
        return new CellMultipleValue(posX, posY);
    }
}
