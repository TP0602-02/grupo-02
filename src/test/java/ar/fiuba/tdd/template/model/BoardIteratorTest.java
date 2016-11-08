package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.circuitverificator.BoardIterator;
import ar.fiuba.tdd.template.entity.Coordinate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardIteratorTest {
    private Board board;
    private BoardIterator iterator;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, "");
        this.iterator = new BoardIterator();
    }

    @Test
    public void moveUpInBorder_ReturnNull() {
        Assert.assertNull(this.iterator.moveUp(this.board, this.board.getCell(new Coordinate(0, 3))));
    }

    @Test
    public void moveDownInBorder_ReturnNull() {
        Assert.assertNull(this.iterator.moveDown(this.board, this.board.getCell(new Coordinate(3, 2))));
    }

    @Test
    public void moveRightInBorder_ReturnNull() {
        Assert.assertNull(this.iterator.moveRight(this.board, this.board.getCell(new Coordinate(1, 3))));
    }

    @Test
    public void moveLeftInBorder_ReturnNull() {
        Assert.assertNull(this.iterator.moveLeft(this.board, this.board.getCell(new Coordinate(2, 0))));
    }

    @Test
    public void moveLeftGetCorrectCoordinates_ReturnTrue() {
        Cell cell = this.iterator.moveLeft(this.board, this.board.getCell(new Coordinate(2, 2)));
        Cell cell2 = this.board.getCell(new Coordinate(2, 1));
        Assert.assertTrue(cell.getColumn() == cell2.getColumn() && cell.getRow() == cell2.getRow());
    }

    @Test
    public void moveRightGetCorrectCoordinates_ReturnTrue() {
        Cell cell = this.iterator.moveRight(this.board, this.board.getCell(new Coordinate(2, 2)));
        Cell cell2 = this.board.getCell(new Coordinate(2, 3));
        Assert.assertTrue(cell.getColumn() == cell2.getColumn() && cell.getRow() == cell2.getRow());
    }

    @Test
    public void moveUpGetCorrectCoordinates_ReturnTrue() {
        Cell cell = this.iterator.moveUp(this.board, this.board.getCell(new Coordinate(2, 2)));
        Cell cell2 = this.board.getCell(new Coordinate(1, 2));
        Assert.assertTrue(cell.getColumn() == cell2.getColumn() && cell.getRow() == cell2.getRow());
    }

    @Test
    public void moveDownGetCorrectCoordinates_ReturnTrue() {
        Cell cell = this.iterator.moveDown(this.board, this.board.getCell(new Coordinate(2, 2)));
        Cell cell2 = this.board.getCell(new Coordinate(3, 2));
        Assert.assertTrue(cell.getColumn() == cell2.getColumn() && cell.getRow() == cell2.getRow());
    }
}
