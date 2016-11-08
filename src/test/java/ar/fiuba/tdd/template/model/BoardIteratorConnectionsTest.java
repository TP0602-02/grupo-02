package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.circuitverificator.BoardIteratorConnections;
import ar.fiuba.tdd.template.entity.Coordinate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardIteratorConnectionsTest {
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;

    private BoardIteratorConnections iterator;
    private Board board;

    @Before
    public void setUp() {
        this.iterator = new BoardIteratorConnections();
        this.board = new Board(4, 4, "");
    }

    @Test
    public void insertValidMovement_returnTrue() {
        Assert.assertTrue(iterator.validateDirection(board, board.getCell(new Coordinate(2, 2)), UP));
    }

    @Test
    public void insertRightValidMovement_returnFalse() {
        Assert.assertFalse(iterator.validateDirection(board, board.getCell(new Coordinate(2, 3)), RIGHT));
    }

    @Test
    public void insertLeftValidMovement_returnFalse() {
        Assert.assertFalse(iterator.validateDirection(board, board.getCell(new Coordinate(2, 0)), LEFT));
    }

    @Test
    public void insertDownValidMovement_returnFalse() {
        Assert.assertFalse(iterator.validateDirection(board, board.getCell(new Coordinate(3, 2)), DOWN));
    }

    @Test
    public void insertUpValidMovement_returnFalse() {
        Assert.assertFalse(iterator.validateDirection(board, board.getCell(new Coordinate(0, 3)), UP));
    }
}
