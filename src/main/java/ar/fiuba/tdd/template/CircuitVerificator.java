package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 09/10/16.
 */


public class CircuitVerificator {

    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;

    private Cell firstCell;
    private int amountOfCellsInTheCircuit;

    public CircuitVerificator() {
        this.firstCell = null;
        this.amountOfCellsInTheCircuit = 0;
    }

    public int getAmountOfCellsInTheCircuit() {
        return this.amountOfCellsInTheCircuit;
    }

    public boolean verificate(Board board) {
        this.firstCell = this.getFirstCellWithValue(board);
        if (this.firstCell != null) {
            ++this.amountOfCellsInTheCircuit;
            int firstCellDirection = this.firstCell.getContents().get(0).getNumberValue();
            if (checkCircuit(this.firstCell, firstCellDirection, board)
                    && this.amountOfCellsInTheCircuit == this.getAmountOfCellsWithValueInTheBoard(board)) {
                return true;
            }
            return false;
        } else {
            return false;
        }

    }

    private boolean checkCircuit(Cell cell, int direction, Board board) {
        Cell nextCell = this.getNextCell(board, cell, direction);
        ++this.amountOfCellsInTheCircuit;
        if (nextCell == this.firstCell) {
            --this.amountOfCellsInTheCircuit;
            return true;
        } else {
            int nextCellDirection = this.getNextDirection(nextCell, direction);
            if (nextCellDirection != -1) {
                return this.checkCircuit(nextCell, nextCellDirection, board);
            }
            return false;
        }
    }

    private int getNextDirection(Cell nextCell, int direction) {
        int oppositeDirection = this.getOppositeDirection(direction);
        for (CellContent cellContent : nextCell.getContents()) {
            if (cellContent.getNumberValue() != oppositeDirection) {
                return cellContent.getNumberValue();
            }
        }
        return -1;
    }

    public int getOppositeDirection(int direction) {
        switch (direction) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            default:
                return 0;
        }
    }

    public Cell getNextCell(Board board, Cell previousCell, int direction) {
        switch (direction) {
            case LEFT:
                return board.getCell(previousCell.getRow(), previousCell.getColumn() - 1);
            case RIGHT:
                return board.getCell(previousCell.getRow(), previousCell.getColumn() + 1);
            case UP:
                return board.getCell(previousCell.getRow() - 1, previousCell.getColumn());
            case DOWN:
                return board.getCell(previousCell.getRow() + 1, previousCell.getColumn());
            default:
                return null;
        }
    }

    private Cell getFirstCellWithValue(Board board) {
        for (int row = 0; row < board.getWidth(); ++row) {
            for (int column = 0; column < board.getHeight(); ++column) {
                Cell cell = board.getCell(row, column);
                if (cell.getContents().size() > 0) {
                    return cell;
                }
            }
        }
        return null;
    }

    public int getAmountOfCellsWithValueInTheBoard(Board board) {
        int total = 0;
        for (Region region : board.getRegions()) {
            total += region.getOcuppiedCells();
        }
        return total;
    }

    public boolean validateDirection(Board board, Cell cell, int direction) {
        switch (direction) {
            case LEFT:
                return cell.getColumn() != 0;
            case RIGHT:
                return cell.getColumn() != (board.getWidth() - 1);
            case UP:
                return cell.getRow() != 0;
            case DOWN:
                return cell.getRow() != (board.getHeight() - 1);
            default:
                return true;
        }

    }
}
