package ar.fiuba.tdd.template.circuitverificator;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.entity.Constants;
import ar.fiuba.tdd.template.entity.Coordinate;

import java.util.ArrayList;

/**
 * Created by alazraqui on 16/10/2016.
 */
public class BoardIteratorConnections {

    private ArrayList<Integer> upperLeftValues = new ArrayList<Integer>();
    private ArrayList<Integer> upperRightValues = new ArrayList<Integer>();
    private ArrayList<Integer> lowerLeftValues = new ArrayList<Integer>();
    private ArrayList<Integer> lowerRightValues = new ArrayList<Integer>();

    public BoardIteratorConnections() {
        this.setUpperLeftValues();
        this.setUpperRightValues();
        this.setLowerLeftValues();
        this.setLowerRightValues();
    }

    private void setLowerRightValues() {
        this.lowerRightValues.add(Constants.RIGHT_VALUE);
        this.lowerRightValues.add(Constants.DOWN_RIGHT_VALUE);
        this.lowerRightValues.add(Constants.DOWN_VALUE);
    }

    private void setLowerLeftValues() {
        this.lowerLeftValues.add(Constants.LEFT_VALUE);
        this.lowerLeftValues.add(Constants.DOWN_LEFT_VALUE);
        this.lowerLeftValues.add(Constants.DOWN_VALUE);
    }

    private void setUpperRightValues() {
        this.upperRightValues.add(Constants.RIGHT_VALUE);
        this.upperRightValues.add(Constants.UP_RIGHT_VALUE);
        this.upperRightValues.add(Constants.UP_VALUE);
    }

    private void setUpperLeftValues() {
        this.upperLeftValues.add(Constants.LEFT_VALUE);
        this.upperLeftValues.add(Constants.UP_LEFT_VALUE);
        this.upperLeftValues.add(Constants.UP_VALUE);
    }

    public Cell getNextCell(Board board, Cell previousCell, int direction) {
        boolean isValid = this.validateDirection(board, previousCell, direction);
        if (isValid && direction < Constants.UP_LEFT_VALUE) {
            return this.getNextCellNormal(board, previousCell, direction);
        } else {
            if (isValid && direction > Constants.DOWN_VALUE) {
                return this.getNextCellDiagonal(board, previousCell, direction);
            }
        }
        return null;
    }

    private Cell getNextCellDiagonal(Board board, Cell previousCell, int direction) {
        switch (direction) {
            case Constants.UP_LEFT_VALUE:
                return board.getCell(new Coordinate(previousCell.getRow() - 1, previousCell.getColumn() - 1));
            case Constants.UP_RIGHT_VALUE:
                return board.getCell(new Coordinate(previousCell.getRow() - 1, previousCell.getColumn() + 1));
            case Constants.DOWN_LEFT_VALUE:
                return board.getCell(new Coordinate(previousCell.getRow() + 1, previousCell.getColumn() - 1));
            case Constants.DOWN_RIGHT_VALUE:
                return board.getCell(new Coordinate(previousCell.getRow() + 1, previousCell.getColumn() + 1));
            default:
                return null;
        }
    }

    private Cell getNextCellNormal(Board board, Cell previousCell, int direction) {
        switch (direction) {
            case Constants.LEFT_VALUE:
                return board.getCell(new Coordinate(previousCell.getRow(), previousCell.getColumn() - 1));
            case Constants.RIGHT_VALUE:
                return board.getCell(new Coordinate(previousCell.getRow(), previousCell.getColumn() + 1));
            case Constants.UP_VALUE:
                return board.getCell(new Coordinate(previousCell.getRow() - 1, previousCell.getColumn()));
            case Constants.DOWN_VALUE:
                return board.getCell(new Coordinate(previousCell.getRow() + 1, previousCell.getColumn()));
            default:
                return null;
        }
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
            case Constants.UP_LEFT_VALUE:
                return (cell.getRow() != 0 && cell.getColumn() != 0);
            case Constants.UP_RIGHT_VALUE:
                return (cell.getRow() != 0 && cell.getColumn() != (board.getWidth() - 1));
            case Constants.DOWN_LEFT_VALUE:
                return (cell.getRow() != (board.getHeight() - 1) && cell.getColumn() != 0);
            case Constants.DOWN_RIGHT_VALUE:
                return (cell.getRow() != (board.getHeight() - 1) && cell.getColumn() != (board.getWidth() - 1));
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

    public int getOppositeCorner(CellContent corner) {
        switch (corner.getNumberValue()) {
            case Constants.UPPER_LEFT_CORNER:
                return Constants.LOWER_RIGHT_CORNER;
            case Constants.UPPER_RIGHT_CORNER:
                return Constants.LOWER_LEFT_CORNER;
            case Constants.LOWER_LEFT_CORNER:
                return Constants.UPPER_RIGHT_CORNER;
            case Constants.LOWER_RIGHT_CORNER:
                return Constants.UPPER_LEFT_CORNER;
            default:
                return -1;
        }
    }

    public ArrayList<Cell> getLimitCells(Board board, Cell cell, CellContent corner) {
        switch (corner.getNumberValue()) {
            case Constants.UPPER_LEFT_CORNER:
                return this.getLimitNotNullCells(board, cell, this.upperLeftValues);
            case Constants.UPPER_RIGHT_CORNER:
                return this.getLimitNotNullCells(board, cell, this.upperRightValues);
            case Constants.LOWER_LEFT_CORNER:
                return this.getLimitNotNullCells(board, cell, this.lowerLeftValues);
            case Constants.LOWER_RIGHT_CORNER:
                return this.getLimitNotNullCells(board, cell, this.lowerRightValues);
            default:
                return null;
        }
    }

    private ArrayList<Cell> getLimitNotNullCells(Board board, Cell cell, ArrayList<Integer> values) {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        for (Integer value : values) {
            cells.add(this.getNextCell(board, cell, value));
        }
        return this.getNotNullCells(cells);
    }


    private ArrayList<Cell> getNotNullCells(ArrayList<Cell> possibleCells) {
        ArrayList<Cell> limitCells = new ArrayList<Cell>();
        for (Cell possibleCell : possibleCells) {
            if (possibleCell != null) {
                limitCells.add(possibleCell);
            }
        }
        return limitCells;
    }

    public boolean cellsAreConnected(Cell limitCell, Cell cell) {
        boolean hasSameValue = cellsHasSameValue(limitCell, cell);
        boolean areDiagonals = cellsAreDiagonals(limitCell, cell);
        return (areDiagonals == hasSameValue);
    }

    private boolean cellsAreDiagonals(Cell limitCell, Cell cell) {
        Coordinate coordFirstCell = new Coordinate(limitCell.getRow(), limitCell.getColumn());
        Coordinate coordSecondCell = new Coordinate(cell.getRow(), cell.getColumn());
        return (coordFirstCell.getRow() != coordSecondCell.getRow()
                && coordFirstCell.getColumn() != coordSecondCell.getColumn());
    }


    private boolean cellsHasSameValue(Cell limitCell, Cell secondCell) {
        //get any value of limits cell to know if is the same value in second cell
        ArrayList<String> limitCellDeletebleValues = this.transformToCorners(limitCell.getDeleteableValues());
        ArrayList<String> secondCellDeletebleValues = this.transformToCorners(secondCell.getDeleteableValues());
        for (String value : secondCellDeletebleValues) {
            if (value.equals(limitCellDeletebleValues.get(0))) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<String> transformToCorners(ArrayList<String> deleteableValues) {
        ArrayList<String> numberValues = new ArrayList<String>();
        String value = deleteableValues.get(0);
        switch (value) {
            case "/": numberValues.add("2");
                      numberValues.add("3");
                      return numberValues;
            case "\\": numberValues.add("1");
                       numberValues.add("4");
                       return numberValues;
            default: return deleteableValues;
        }
    }

}
