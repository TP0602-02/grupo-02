package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.rules.GenericRule;

import java.util.ArrayList;

public class Puzzle {

    private Board board;
    private GenericRule firstRule;
    private int boardHeight;
    private int boardWidth;
    private ArrayList<Cell> initialCells;

    public Puzzle(int boardHeight, int boardWidth,
                  GenericRule firstRule, ArrayList<Cell> initialCells) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.board = new Board(boardHeight, boardWidth);
        setInitialCells(initialCells);
        this.initialCells = initialCells;
        this.firstRule = firstRule;
    }

    public ArrayList<Cell> getInitialCells() {
        return this.initialCells;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public Cell getCell(int row, int column) {
        return this.board.getCell(row, column);
    }

    private void setInitialCells(ArrayList<Cell> initialCells) {
        for (Cell cellToAdd : initialCells) {
            this.board.setValues(cellToAdd.getRow(), cellToAdd.getColumn(), cellToAdd.getContents());
        }
    }

    public boolean checkMovement(Play play) {
        Cell cell = play.getSelectedCell();
        if (this.validateMove(cell,Integer.parseInt(String.valueOf(cell.getContents().get(0).getValue())))) {
            // this.board.setValue(cell.getRow(), cell.getColumn(), new ValueContent<Integer>(valueToAdd));
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMovement(Cell cell, int valueToAdd) {
        // TODO: replace parameters by Play class instead of cell and valueToAdd
        if (this.validateMove(cell, valueToAdd)) {
            // this.board.setValue(cell.getRow(), cell.getColumn(), new ValueContent<Integer>(valueToAdd));
            return true;
        } else {
            return false;
        }
    }

    private boolean validateMove(Cell cell, int valueToAdd) {
        return this.firstRule.validate(this.board, cell, valueToAdd);
    }

}

