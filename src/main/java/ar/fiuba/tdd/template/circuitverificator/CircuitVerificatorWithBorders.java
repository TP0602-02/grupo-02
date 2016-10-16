package ar.fiuba.tdd.template.circuitverificator;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Constants;
import ar.fiuba.tdd.template.entity.Coordinate;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 14/10/16.
 */
public class CircuitVerificatorWithBorders extends CircuitVerificator {

    private ArrayList<Cell> circuitCells = new ArrayList<Cell>();

    @Override
    public boolean isCircuitClosed(Board board) {
        this.cleanCircuitCells();
        Cell firstCellInTheCircuit = this.getFirstCellInsideCircuit(board);
        if (firstCellInTheCircuit != null) {
            return (checkAllDirections(board, firstCellInTheCircuit) && (hasValidValues(board)));
        }
        return false;
    }

    private void cleanCircuitCells() {
        this.circuitCells.clear();
    }

    private boolean checkAllDirections(Board board, Cell cell) {
        this.addCellToTheCircuit(cell);
        ArrayList<Integer> validDirections = this.getValidDirections(cell);
        for (Integer direction : validDirections) {
            if (this.hasOpenRoads(board, cell, direction)) {
                return false;
            }
        }
        this.isClose = true;
        return true;
    }

    private boolean hasValidValues(Board board) {
        if (!this.isClose) {
            return false;
        }
        return hasValidContents(board);
    }

    private boolean hasValidContents(Board board) {
        for (int col = 0; col < board.getWidth(); col++) {
            for (int row = 0; row < board.getHeight(); row++) {
                Cell cell = board.getCell(row, col);
                if (cell.getSummableContents().size() != 0) {
                    if (!cellHasValidValues(board, cell)) {
                        this.isClose = true;
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean cellHasValidValues(Board board, Cell cell) {
        if (!this.circuitCells.contains(cell)) {
            return (nextCellOutsideCircuite(board, cell));
        } else {
            return (!nextCellOutsideCircuite(board, cell));
        }
    }

    private boolean nextCellOutsideCircuite(Board board, Cell cell) {
        for (CellContent content : cell.getSummableContents()) {
            Cell nextCell = this.iterator.getNextCell(board, cell, content.getNumberValue());
            if (!this.circuitCells.contains(nextCell)) {
                return false;
            }
        }
        return true;
    }

    private void addCellToTheCircuit(Cell cell) {
        if (!this.circuitCells.contains(cell)) {
            this.circuitCells.add(cell);
        }
    }

    private boolean isCellInTheCircuit(Cell cell) {
        return this.circuitCells.contains(cell);
    }

    private boolean hasOpenRoads(Board board, Cell previousCell, Integer previousDirection) {
        Cell cell = this.iterator.getNextCell(board, previousCell, previousDirection);
        if (cell == null) {
            return true;
        }
        if (this.isACloseRoad(cell, previousDirection) || this.isCellInTheCircuit(cell)) {
            this.addCellToTheCircuit(cell);
            return false;
        } else {
            this.addCellToTheCircuit(cell);
            return this.verificateAllRoads(board, cell, previousDirection);
        }
    }

    private boolean verificateAllRoads(Board board, Cell cell, Integer previousDirection) {
        ArrayList<Integer> directions = this.getValidDirectionsWithoutPreviousDirection(cell, previousDirection);
        for (Integer direction : directions) {
            if (this.hasOpenRoads(board, cell, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean isACloseRoad(Cell cell, Integer previousDirection) {
        return (this.getValidDirectionsWithoutPreviousDirection(cell, previousDirection).size() == 0);
    }

    private ArrayList<Integer> getValidDirectionsWithoutPreviousDirection(Cell cell, Integer direction) {
        ArrayList<Integer> validDirections = this.getValidDirections(cell);
        Integer comingDirection = this.iterator.getOppositeDirection(direction);
        validDirections.remove(comingDirection);
        return validDirections;
    }


    private ArrayList<Integer> getValidDirections(Cell cell) {
        ArrayList<Integer> directions = this.getAllDirections();
        for (CellContent cellContent : cell.getContents()) {
            directions.remove((Integer) cellContent.getNumberValue());
        }
        return directions;
    }

    private ArrayList<Integer> getAllDirections() {
        ArrayList<Integer> directions = new ArrayList<Integer>();
        directions.add(Constants.LEFT_VALUE);
        directions.add(Constants.RIGHT_VALUE);
        directions.add(Constants.UP_VALUE);
        directions.add(Constants.DOWN_VALUE);
        return directions;
    }

    private Cell getFirstCellInsideCircuit(Board board) {
        Cell cell = this.getFirstCellWithValue(board);
        ArrayList<CellContent> contents = cell.getSummableContents();
        for (CellContent content : contents) {
            if (!this.iterator.validateDirection(board, cell, content.getNumberValue())) {
                return cell;
            }
        }
        return this.iterator.getNextCell(board, cell, contents.get(0).getNumberValue()); //es lo mismo cual le paso
    }
}
