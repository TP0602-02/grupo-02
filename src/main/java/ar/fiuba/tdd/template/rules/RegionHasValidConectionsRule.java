package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.region.Region;

import java.util.ArrayList;

public class RegionHasValidConectionsRule extends RegionConectionRule {

    private static final int MAX_CONECTIONS_REGION = 2;

    @Override
    protected boolean validationInMoreThanOneRegion(Board board, Cell cell, Cell nextCell) {
        return this.validateConectionsInRegion(board, cell, nextCell);
    }

    @Override
    protected boolean validationInSameRegion(Board board, Cell cell, Cell nextCell) {
        return true;
    }

    private boolean validateConectionsInRegion(Board board, Cell cell, Cell nextCell) {
        return (validateInOneRegion(board, cell) && validateInOneRegion(board, nextCell));
    }

    private boolean validateInOneRegion(Board board, Cell cell) {
        Region region = board.getCellRegions(cell).get(0);
        ArrayList<Cell> cellsInRegion = region.getCells();
        int conections = 0;
        for (Cell actualCell : cellsInRegion) {
            for (CellContent content : actualCell.getContents()) {
                Cell nextCell = this.iterator.getNextCell(board, actualCell, content.getNumberValue());
                if (!region.containsCell(nextCell)) {
                    conections++;
                }
            }
        }
        return conections < MAX_CONECTIONS_REGION;
    }

    @Override
    public String toString() {
        return "La region en cuestion ya tiene una entrada y una salida";
    }
}
