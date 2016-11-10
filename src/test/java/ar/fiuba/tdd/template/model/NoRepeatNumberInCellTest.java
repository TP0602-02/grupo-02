package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.Play;
import ar.fiuba.tdd.template.rules.NoRepeatNumberInCell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NoRepeatNumberInCellTest {
    private Cell cell;
    private NoRepeatNumberInCell rule;
    private Board board;

    @Before
    public void setUp() {
        CellFactory cellFactory = new CellFactory();
        this.cell = cellFactory.createCell("", new Coordinate(0, 0));
        this.rule = new NoRepeatNumberInCell();
        this.board = new Board(4, 4, "");
    }

    @Test
    public void addNumberInEmptyCell_ReturnTrue() {
        Play play = new Play(this.cell, "2");
        Assert.assertTrue(this.rule.validate(board, play));
    }

    @Test
    public void addNumberInCellWithOtherValues_ReturnTrue() {
        this.cell.addContent(new ValueContent(1));
        this.cell.addContent(new ValueContent(3));
        this.cell.addContent(new ValueContent(4));
        Play play = new Play(this.cell, "2");
        Assert.assertTrue(this.rule.validate(board, play));
    }


    @Test
    public void addValueEqualClue_returnTrue() {
        this.cell.addContent(new ClueContent(1));
        Play play = new Play(this.cell, "1");
        Assert.assertTrue(this.rule.validate(board, play));
    }

    @Test
    public void repeatValue_returnFalse() {
        this.cell.addContent(new ValueContent(1));
        Play play = new Play(this.cell, "1");
        Assert.assertFalse(this.rule.validate(board, play));
    }
}
