package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.rules.GenericRule;

/**
 * Created by matiaskamien on 27/09/16.
 */
public class Puzzle {

    private Board board;
    private GenericRule firstRule;

    public Puzzle(int boardHeight, int boardWidth, GenericRule firstRule) {
        this.board = new Board(boardHeight, boardWidth);
        this.firstRule = firstRule;
    }

    public boolean validateMove(Cell cell, int valueToAdd) {
        this.firstRule.validate(this.board, cell, valueToAdd);
    }
}
