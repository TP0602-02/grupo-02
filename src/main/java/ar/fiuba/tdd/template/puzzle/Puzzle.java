package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.rules.GenericRule;

import java.util.ArrayList;

public class Puzzle {

    private Board board;
    private ArrayList<GenericRule> rules;
    private int boardHeight;
    private int boardWidth;
    private ArrayList<Cell> initialCells;

    public Puzzle(int boardHeight, int boardWidth,
                  ArrayList<GenericRule> rules, ArrayList<Cell> initialCells) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        //TODO como ultimo parametro hay que pasarle lo que se levante del parser del archivo

        this.board = new Board(boardHeight, boardWidth, CellFactory.CELL_SINGLE_VALUE);
        setInitialCells(initialCells);
        this.initialCells = initialCells;
        this.rules = new ArrayList<GenericRule>();
        for (GenericRule rule : rules) {
            this.rules.add(rule);
        }
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

    public boolean checkMovement(Cell cell, int valueToAdd) {
        if (this.validateMove(cell, valueToAdd)) {
            // this.board.setValue(cell.getRow(), cell.getColumn(), new ValueContent<Integer>(valueToAdd));
            return true;
        } else {
            return false;
        }
    }

    private boolean validateMove(Cell cell, int valueToAdd) {
        //TODO DESCOMENTAR ESTO CUANADO SE CREEN BIEN LOS NOMBRES DE LAS RULES CON LA FACTORY!!!
       /* for (GenericRule rule : this.rules) {
            if (!rule.validate(this.board, cell, valueToAdd)) {
                return false;
            }
        }*/
        return true;
    }

}

