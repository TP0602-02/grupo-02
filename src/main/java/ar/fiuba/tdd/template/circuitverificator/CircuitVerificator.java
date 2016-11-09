package ar.fiuba.tdd.template.circuitverificator;


import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.entity.Coordinate;

/**
 * Created by alazraqui on 15/10/2016.
 */
public abstract class CircuitVerificator {

    protected Cell firstCell;
    protected int amountOfCellsInTheCircuit;
    protected boolean isClose;
    protected BoardIteratorConnections iterator;

    public CircuitVerificator() {
        this.isClose = false;
        this.iterator = new BoardIteratorConnections();
    }

    public abstract boolean isCircuitClosed(Board board);

    public Cell getNextCell(Board board, Cell previousCell, int direction) {
        return this.iterator.getNextCell(board, previousCell, direction);
    }

    public Cell getFirstCellWithValue(Board board) {
        for (int row = 0; row < board.getWidth(); ++row) {
            for (int column = 0; column < board.getHeight(); ++column) {
                Cell cell = board.getCell(new Coordinate(row, column));
                if (cell.getFirstEditableContent() != null) {
                    return cell;
                }
            }
        }
        return null;
    }

    public boolean hasLinesOutOfTheCircuit(Board board) {
        return (this.amountOfCellsInTheCircuit != this.getAmountOfCellsWithValueInTheBoard(board));
    }

    public int getAmountOfCellsWithValueInTheBoard(Board board) {
        int total = 0;
        for (Region region : board.getRegions()) {
            total += region.getOcuppiedCells();
        }
        return total;
    }


    public void setCell(Cell cell) {
    }
}
