package ar.fiuba.tdd.template.circuitverificator;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.Constants;

/**
 * Created by alazraqui on 15/10/2016.
 */
public abstract class CircuitVerificator {

    protected Cell firstCell;
    protected int amountOfCellsInTheCircuit;

    public abstract boolean isCircuitClosed(Board board);

    public Cell getNextCell(Board board, Cell previousCell, int direction) {
        if (this.validateDirection(board, previousCell, direction)) {
            switch (direction) {
                case Constants.IZQUIERDA_VALUE:
                    return board.getCell(previousCell.getRow(), previousCell.getColumn() - 1);
                case Constants.DERECHA_VALUE:
                    return board.getCell(previousCell.getRow(), previousCell.getColumn() + 1);
                case Constants.ARRIBA_VALUE:
                    return board.getCell(previousCell.getRow() - 1, previousCell.getColumn());
                case Constants.ABAJO_VALUE:
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
            case Constants.IZQUIERDA_VALUE:
                return Constants.DERECHA_VALUE;
            case Constants.DERECHA_VALUE:
                return Constants.IZQUIERDA_VALUE;
            case Constants.ARRIBA_VALUE:
                return Constants.ABAJO_VALUE;
            case Constants.ABAJO_VALUE:
                return Constants.ARRIBA_VALUE;
            default:
                return 0;
        }
    }

    public boolean validateDirection(Board board, Cell cell, int direction) {
        switch (direction) {
            case Constants.IZQUIERDA_VALUE:
                return cell.getColumn() != 0;
            case Constants.DERECHA_VALUE:
                return cell.getColumn() != (board.getWidth() - 1);
            case Constants.ARRIBA_VALUE:
                return cell.getRow() != 0;
            case Constants.ABAJO_VALUE:
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

    public String getNameOppositeDirection(String direction) {
        if (direction.equals(Constants.IZQUIERDA)) {
            return Constants.DERECHA;
        } else if (direction.equals(Constants.DERECHA)) {
            return Constants.IZQUIERDA;
        } else if (direction.equals(Constants.ARRIBA)) {
            return Constants.ABAJO;
        } else if (direction.equals(Constants.ABAJO)) {
            return Constants.ARRIBA;
        }
        return "";
    }
}
