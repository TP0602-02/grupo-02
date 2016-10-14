package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by alazraqui on 12/10/2016.
 */
public class NumberOfConectionsInRegionRule extends RegionConectionRule {

    private boolean validateRegion(Board board, Cell cell) {
        Region region = board.getCellRegions(cell).get(0);
        int conections = addConection(cell, region.getOcuppiedCells());
        return (conections <= region.getTotal() || region.getTotal() == 0);
    }

    @Override
    public boolean validationInSameRegion(Board board, Cell cell, Cell nextCell) {
        Region region = board.getCellRegions(cell).get(0); //Revisar
        int conections = region.getOcuppiedCells();
        conections = addConection(cell, conections);
        conections = addConection(nextCell, conections);
        return (conections <= region.getTotal());
    }

    private int addConection(Cell cell, int conections) {
        if (cell.isEmpty() || !cell.isSummable()) {
            conections++;
        }
        return conections;
    }

    @Override
    protected boolean validationInMoreThanOneRegion(Board board, Cell cell, Cell nextCell) {
        return (validateRegion(board, cell) && validateRegion(board, nextCell));
    }
}
