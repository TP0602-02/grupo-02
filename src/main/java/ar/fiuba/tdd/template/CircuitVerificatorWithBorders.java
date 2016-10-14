package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 14/10/16.
 */
public class CircuitVerificatorWithBorders extends CircuitVerificator {

    private ArrayList<Cell> circuitCells = new ArrayList<Cell>();

    @Override
    public boolean isCircuitClosed(Board board) {
        this.cleanCircuitCells();
        Cell firstCellInTheCircuit = this.getFirstCellInTheCircuit(board);
        if (firstCellInTheCircuit != null) {
            return checkAllDirections(board, firstCellInTheCircuit);
        }
        return false;
    }

    private void cleanCircuitCells() {
        this.circuitCells.clear();
    }

    private boolean checkAllDirections(Board board, Cell cell) {
        this.addCellToTheCircuit(cell);
        ArrayList<Integer> validDirections = this.getValidDirections(cell);
        for (Integer direction: validDirections) {
            if (this.hasOpenRoads(board, cell, direction)) {
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
        Cell cell = this.getNextCell(board, previousCell, previousDirection);
        this.addCellToTheCircuit(cell);
        if (cell == null) {
            return true;
        }
        if (this.isACloseRoad(cell, previousDirection) || this.isCellInTheCircuit(cell)) {
            return false;
        } else {
            return this.verificateAllRoads(board, cell, previousDirection);
        }
    }

    private boolean verificateAllRoads(Board board, Cell cell, Integer previousDirection) {
        ArrayList<Integer> directions = this.getValidDirectionsWithoutPreviousDirection(cell, previousDirection);
        for (Integer direction: directions) {
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
        Integer comingDirection = this.getOppositeDirection(direction);
        validDirections.remove(comingDirection);
        return validDirections;
    }


    private ArrayList<Integer> getValidDirections(Cell cell) {
        ArrayList<Integer> directions = this.getAllDirections();
        for (CellContent cellContent : cell.getContents()) {
            directions.remove(cellContent.getNumberValue());
        }
        return directions;
    }

    private ArrayList<Integer> getAllDirections() {
        ArrayList<Integer> directions = new ArrayList<Integer>();
        directions.add(LEFT);
        directions.add(RIGHT);
        directions.add(UP);
        directions.add(DOWN);
        return directions;
    }

    private Cell getFirstCellInTheCircuit(Board board) {
        //TODO Ver como agarrar laa primer celda.
        return null;
    }
}