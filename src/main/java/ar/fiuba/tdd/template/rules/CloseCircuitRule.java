package ar.fiuba.tdd.template.rules;


import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithoutBorders;
import ar.fiuba.tdd.template.board.Region;
/**
 * Created by alazraqui on 12/10/2016.
 */
public class CloseCircuitRule extends GenericRule {
    private CircuitVerificatorWithoutBorders verificator;

    public CloseCircuitRule() {
        this.verificator = new CircuitVerificatorWithoutBorders();
    }

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        Cell nextCell = this.verificator.getNextCell(board, cell, numberToAdd);
        CellContent cellContent = new ValueContent(numberToAdd);
        CellContent nextCellContent = new ValueContent(this.verificator.getOppositeDirection(numberToAdd));
        cell.setContent(cellContent);
        nextCell.setContent(nextCellContent);
        nextCell.setContent(nextCellContent);
        if (!verificator.isCircuitClosed(board)) {
            cell.removeContent(cellContent);
            nextCell.removeContent(nextCellContent);
            return true;
        }
        boolean validRegions = validateRegions(board);
        boolean adyacentCells = validateAdyacentCells(board);
        boolean hasLinesOutOfTheCircuit = this.verificator.hasLinesOutOfTheCircuit(board);
        cell.removeContent(cellContent);
        nextCell.removeContent(nextCellContent);
        return (validRegions && adyacentCells && hasLinesOutOfTheCircuit);
    }

    private boolean validateRegions(Board board) {
        for (Region region : board.getRegions()) {
            if (region.getOcuppiedCells() != region.getTotal() && region.getTotal() != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean validateAdyacentCells(Board board) {
        for (Region region : board.getRegions()) {
            for (Cell cell : region.getCells()) {
                if (!cell.isSummable()) {
                    if (!verificate(board, cell)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean verificate(Board board, Cell cell) {
        for (Cell actualCell : board.getAdyacentCells(cell)) {
            if (actualCell.isSummable() || board.cellsInSameRegion(cell, actualCell)) {
                return true;
            }
        }
        return false;
    }
}
