package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.rules.GenericRule;

import java.util.ArrayList;

public class Puzzle {

    private Board board;
    private GenericRule firstRule;
    private int boardHeight;
    private int boardWidth;
    ArrayList<Cell> initialCells;

    public Puzzle(int boardHeight, int boardWidth,
                  GenericRule firstRule, ArrayList<Cell> initialCells) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.board = new Board(boardHeight, boardWidth);
        setInitialCells(initialCells);
        this.initialCells = initialCells;
        this.firstRule = firstRule;
    }

    public ArrayList<Cell>getInitialCells() {
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

    public boolean checkMovement(Cell cell, int valueToAdd) {
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

    public boolean firstRuleIsNull() {
        return this.firstRule == null;
    }

}

