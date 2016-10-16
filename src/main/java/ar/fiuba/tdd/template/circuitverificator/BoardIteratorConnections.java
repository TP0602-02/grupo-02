package ar.fiuba.tdd.template.circuitverificator;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.Constants;

/**
 * Created by alazraqui on 16/10/2016.
 */
public class BoardIteratorConnections {

    public Cell getNextCell(Board board, Cell previousCell, int direction) {
        if (this.validateDirection(board, previousCell, direction)) {
            switch (direction) {
                case Constants.LEFT_VALUE:
                    return board.getCell(previousCell.getRow(), previousCell.getColumn() - 1);
                case Constants.RIGHT_VALUE:
                    return board.getCell(previousCell.getRow(), previousCell.getColumn() + 1);
                case Constants.UP_VALUE:
                    return board.getCell(previousCell.getRow() - 1, previousCell.getColumn());
                case Constants.DOWN_VALUE:
                    return board.getCell(previousCell.getRow() + 1, previousCell.getColumn());
                default:
                    return null;
            }
        }
        return null;
    }

    public boolean validateDirection(Board board, Cell cell, int direction) {
        switch (direction) {
            case Constants.LEFT_VALUE:
                return cell.getColumn() != 0;
            case Constants.RIGHT_VALUE:
                return cell.getColumn() != (board.getWidth() - 1);
            case Constants.UP_VALUE:
                return cell.getRow() != 0;
            case Constants.DOWN_VALUE:
                return cell.getRow() != (board.getHeight() - 1);
            default:
                return true;
        }
    }

    public String getNameOppositeDirection(String direction) {
        if (direction.equals(Constants.LEFT)) {
            return Constants.RIGHT;
        } else if (direction.equals(Constants.RIGHT)) {
            return Constants.LEFT;
        } else if (direction.equals(Constants.UP)) {
            return Constants.DOWN;
        } else if (direction.equals(Constants.DOWN)) {
            return Constants.UP;
        }
        return "";
    }


    public int getOppositeDirection(int direction) {
        switch (direction) {
            case Constants.LEFT_VALUE:
                return Constants.RIGHT_VALUE;
            case Constants.RIGHT_VALUE:
                return Constants.LEFT_VALUE;
            case Constants.UP_VALUE:
                return Constants.DOWN_VALUE;
            case Constants.DOWN_VALUE:
                return Constants.UP_VALUE;
            default:
                return 0;
        }
    }
}
