package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.rules.GenericRule;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 27/09/16.
 */
public class Puzzle {

    private Board board;
    private GenericRule firstRule;
    private ArrayList<Cell> initialCells;

    public Puzzle(int boardHeight, int boardWidth, GenericRule firstRule, ArrayList<Cell> initialCells) {
        this.board = new Board(boardHeight, boardWidth);
        this.firstRule = firstRule;
        this.initialCells = initialCells;
    }

    public ArrayList<Cell> getInitialCells() {
        return initialCells;
    }

    public boolean checkMovement(Cell cell, int valueToAdd) {
        if (this.validateMove(cell, valueToAdd)) {
            this.board.setValue(cell.getRow(), cell.getColumn(), new ValueContent<Integer>(valueToAdd));
            return true;
        } else {
            return false;
        }
    }

    private boolean validateMove(Cell cell, int valueToAdd) {
        return this.firstRule.validate(this.board, cell, valueToAdd);
    }

}
