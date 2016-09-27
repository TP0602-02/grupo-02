package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.BoardIterator;
import ar.fiuba.tdd.template.board.cell.Cell;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 27/09/16.
 */
public class NoRepeatNumberInRegionValidationRule extends NoRepeatValueValidationRule {

    public NoRepeatNumberInRegionValidationRule() {
        this.nextRule = new NullRule();
    }

    @Override
    public ArrayList<Cell> getCellsToValidate(Board board, Cell cell) {
        ArrayList<Cell> horizontalRegionCells = this.getHorizontalRegionCells(board, cell);
        ArrayList<Cell> verticalRegionCells = this.getVerticalRegionCells(board, cell);
        horizontalRegionCells.addAll(verticalRegionCells);
        return horizontalRegionCells;
    }

    private ArrayList<Cell> getHorizontalRegionCells(Board board, Cell cell) {
        ArrayList<Cell> horizontalCells = new ArrayList<Cell>();

        horizontalCells.addAll(this.getGenericMove(board, cell,cell.getColumn(),0, new Board.IterateInOneDirection() {
            @Override
            public Cell iterate(Cell cell) {
                return board.getIterator().getLeftCell(cell);
            }
        }));

        horizontalCells.addAll(this.getGenericMove(board, cell, cell.getColumn(), board.getWidth(), new Board.IterateInOneDirection() {
            @Override
            public Cell iterate(Cell cell) {
                return board.getIterator().getRightCell(cell);
            }
        }));

        return horizontalCells;
    }

    private ArrayList<Cell> getVerticalRegionCells(Board board, Cell cell) {
        ArrayList<Cell> verticalCells = new ArrayList<Cell>();

        verticalCells.addAll(this.getGenericMove(board, cell,cell.getRow(),
                board.getHeight(), new Board.IterateInOneDirection() {
            @Override
            public Cell iterate(Cell cell) {
                return board.getIterator().getBelowCell(cell);
            }
        }));

        verticalCells.addAll(this.getGenericMove(board, cell, cell.getRow(), 0, new Board.IterateInOneDirection() {
            @Override
            public Cell iterate(Cell cell) {
                return board.getIterator().getAboveCell(cell);
            }
        }));
        return verticalCells;
    }


    private ArrayList<Cell> getGenericMove(Board board, Cell cell, int initLimit,int finalLimit
    ,Board.IterateInOneDirection iterator) {
        ArrayList<Cell> aboveCells = new ArrayList<Cell>();
        while (initLimit >= finalLimit) {
            Cell cellToCheck = iterator.iterate(cell);
            if (cellToCheck.isSummable()) {
                aboveCells.add(cellToCheck);
                cell = cellToCheck;
            } else {
                return aboveCells;
            }
        }
        return null;
    }

/*
    private ArrayList<Cell> getAboveCells(Board board, Cell cell) {
        ArrayList<Cell> aboveCells = new ArrayList<Cell>();
        while (cell.getRow() >= 0) {
            Cell cellToCheck = board.getAboveCell(cell);
            if (cellToCheck.isSummable()) {
                aboveCells.add(cellToCheck);
                cell = cellToCheck;
            } else {
                return aboveCells;
            }
        }
        return null;
    }

    private ArrayList<Cell> getBelowCells(Board board, Cell cell) {
        ArrayList<Cell> belowCells = new ArrayList<Cell>();
        while (cell.getRow() < board.getHeight()) {
            Cell cellToCheck = board.getBelowCell(cell);
            if (cellToCheck.isSummable()) {
                belowCells.add(cellToCheck);
                cell = cellToCheck;
            } else {
                return belowCells;
            }
        }
        return null;
    }


    private ArrayList<Cell> getLeftCells(Board board, Cell cell) {
        ArrayList<Cell> leftCells = new ArrayList<Cell>();
        while (cell.getColumn() >= 0) {
            Cell cellToCheck = board.getLeftCell(cell);
            if (cellToCheck.isSummable()) {
                leftCells.add(cellToCheck);
                cell = cellToCheck;
            } else {
                return leftCells;
            }
        }
        return null;
    }

    private ArrayList<Cell> getRightCells(Board board, Cell cell) {
        ArrayList<Cell> rightCells = new ArrayList<Cell>();
        while (cell.getColumn() < board.getWidth()) {
            Cell cellToCheck = board.getRightCell(cell);
            if (cellToCheck.isSummable()) {
                rightCells.add(cellToCheck);
                cell = cellToCheck;
            } else {
                return rightCells;
            }
        }
        return null;
    }*/
}
