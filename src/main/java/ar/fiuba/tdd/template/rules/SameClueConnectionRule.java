package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;

public class SameClueConnectionRule extends GenericRule {
    private BoardIteratorConnections iterator = new BoardIteratorConnections();
    private int clueValue = -1;

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        Cell nextCell = this.iterator.getNextCell(board, cell, numberToAdd);
        Cell cellWithClue = this.getCellWithClue(cell, nextCell);
        if (cellWithClue == null) {
            return true;
        }
        this.generatePlay(cell, numberToAdd, nextCell);
        this.clueValue = this.getClueValue(cellWithClue);
        if (this.validConnection(board, cellWithClue, this.getPreviousDirection(cellWithClue))) {
            this.removePlay(cell, nextCell);
            return true;
        }
        this.removePlay(cell, nextCell);
        return false;
    }

    private int getPreviousDirection(Cell cellWithClue) {
        for (CellContent content: cellWithClue.getContents()) {
            if (content.isSummable()) {
                return content.getNumberValue();
            }
        }
        return -1;
    }

    private void generatePlay(Cell cell, int numberToAdd, Cell nextCell) {
        cell.getContents().add(new ValueContent(numberToAdd));
        int nextCellValue = this.iterator.getOppositeDirection(numberToAdd);
        nextCell.getContents().add(new ValueContent( nextCellValue ));
    }

    private void removePlay(Cell cell, Cell nextCell) {
        int lastContentIndex = cell.getContents().size() - 1;
        cell.getContents().remove( lastContentIndex );
        lastContentIndex = nextCell.getContents().size() - 1;
        nextCell.getContents().remove( lastContentIndex );
    }

    private boolean validConnection(Board board, Cell cell, int previousDirection) {
        Cell nextCell = this.iterator.getNextCell(board, cell, previousDirection);
        if (nextCell == null) {
            return true;
        }
        if (this.hasClue(nextCell) && this.isSameValue(nextCell)) {
            return true;
        } else if (this.hasClue(nextCell) && !this.isSameValue(nextCell)) {
            return false;
        }
        return this.validConnection(board, nextCell, this.getOtherValue(nextCell,this.iterator.getOppositeDirection(previousDirection)));
    }

    private boolean isSameValue(Cell cell) {
        return this.clueValue == this.getClueValue(cell);
    }

    private int getOtherValue(Cell nextCell, int previousValue) {
        for (CellContent content: nextCell.getContents()) {
            if (content.getNumberValue() != previousValue) {
                return content.getNumberValue();
            }
        }
        return -1;
    }

    private int getClueValue(Cell cell) {
        for (CellContent content: cell.getContents()) {
            if (!content.isSummable()) {
                return content.getNumberValue();
            }
        }
        return -1;
    }

    private Cell getCellWithClue(Cell cell, Cell nextCell) {
        if (this.hasClue(cell)) {
            return cell;
        } else if (this.hasClue(nextCell)) {
            return nextCell;
        }
        return null;
    }

    private boolean hasClue(Cell cell) {
        return (cell.getContents().size() > cell.getSummableContents().size());
    }
}