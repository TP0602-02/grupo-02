package ar.fiuba.tdd.template.circuitverificator;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by alazraqui on 15/10/2016.
 */
public abstract class CircuitVerificator {


    protected static final int LEFT = 1;
    protected static final int RIGHT = 2;
    protected static final int UP = 3;
    protected static final int DOWN = 4;

    protected Cell firstCell;

    protected int amountOfCellsInTheCircuit;

    public abstract boolean isCircuitClosed(Board board);

    public Cell getNextCell(Board board, Cell previousCell, int direction) {
        if (this.validateDirection(board, previousCell, direction)) {
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
        return null;
    }

    public Cell getFirstCellWithValue(Board board) {
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

    public boolean hasLinesOutOfTheCircuit(Board board) {
        return (this.amountOfCellsInTheCircuit < this.getAmountOfCellsWithValueInTheBoard(board));
    }

    public int getAmountOfCellsWithValueInTheBoard(Board board) {
        int total = 0;
        for (Region region : board.getRegions()) {
            total += region.getOcuppiedCells();
        }
        return total;
    }
}
