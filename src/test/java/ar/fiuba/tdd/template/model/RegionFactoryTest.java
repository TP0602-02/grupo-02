package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.RegionFactory;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellSingleValue;
import org.junit.Test;

import java.util.ArrayList;

public class RegionFactoryTest {

    @Test
    public void createsRegionsOnBoard() {

        Cell topLeft = new CellSingleValue(0, 0);
        Cell bottomRight = new CellSingleValue(5, 5);
        ArrayList<Cell> exceptions = new ArrayList<>();
        Cell exceptionCell = new CellSingleValue(1, 1);
        exceptions.add(exceptionCell);

        Region region = RegionFactory.getFactory().createRegion(topLeft, bottomRight, exceptions, "GENERIC REGION");

        if (region.getCells().contains(exceptionCell)) {
            assert false;
        } else {
            assert true;
        }

        //System.out.print("Number of cells should be 35: (6 * 6 - 1) " + region.getCells().size());
    }

}
