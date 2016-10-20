package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificator;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithBorders;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithoutBorders;

/**
 * Created by matiaskamien on 15/10/16.
 */
public abstract class GenericCloseCircuitRule extends GenericRule {

    protected CircuitVerificator verificator;
    protected BoardIteratorConnections iterator;
    protected GenericTotalRegionRule totalRegionRule;
    protected Cell cell;
    protected Cell nextCell;

    public GenericCloseCircuitRule() {
        this.iterator = new BoardIteratorConnections();
        this.cell = null;
        this.nextCell = null;
    }

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        this.setCells(board, cell, numberToAdd);
        this.generateMovement(numberToAdd);
        if (!verificator.isCircuitClosed(board)) {
            this.removeContents();
            return true;
        }
        return this.validateMovement(board);
    }

    private boolean validateMovement(Board board) {
        boolean validRegions = this.validateTotalRegionRule(board);
        boolean hasLinesOutOfTheCircuit = this.verificator.hasLinesOutOfTheCircuit(board);
        boolean checkOtherMethods = this.checkOtherMethods(board);
        this.removeContents();
        return (validRegions && checkOtherMethods && hasLinesOutOfTheCircuit);
    }

    protected boolean validateTotalRegionRule(Board board) {
        return this.totalRegionRule.validate(board);
    }

    protected abstract boolean checkOtherMethods(Board board);

    private void setCells(Board board, Cell cell, int numberToAdd) {
        this.cell = cell;
        this.nextCell = this.iterator.getNextCell(board, cell, numberToAdd);
    }

    private void removeContents() {
        this.removeLastContent(this.cell);
        if (this.nextCell != null) {
            this.removeLastContent(this.nextCell);
        }
    }

    private void removeLastContent(Cell cell) {
        int lastContentPosition = cell.getContents().size() - 1;
        CellContent lastContent = cell.getContents().get(lastContentPosition);
        cell.removeContent(lastContent);
    }

    private void generateMovement(int numberToAdd) {
        CellContent cellContent = new ValueContent(numberToAdd);
        CellContent nextCellContent = new ValueContent(this.iterator.getOppositeDirection(numberToAdd));
        this.cell.setContent(cellContent);
        if (this.nextCell != null) {
            this.nextCell.setContent(nextCellContent);
        }
    }
}