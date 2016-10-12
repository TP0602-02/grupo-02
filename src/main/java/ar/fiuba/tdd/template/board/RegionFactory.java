package ar.fiuba.tdd.template.board;

import ar.fiuba.tdd.template.board.cell.model.Cell;

import java.util.ArrayList;

/**
 * Created by martin on 10/12/16.
 */

public class RegionFactory {

    public static final String GENERIC_REGION = "GENERIC REGION";

    public Region createRegion(Cell topLeft, Cell bottomRight, ArrayList<Cell> exceptions, String regionType) {
        if ( regionType.equals(GENERIC_REGION)) {
            return new GenericRegion().getRegion(topLeft, bottomRight, exceptions);
        }
        return new Region(exceptions);
    }


}

