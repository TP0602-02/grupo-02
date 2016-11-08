package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.ValidRegionRangeRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ValidRegionRangeRuleTest {
    private ValidRegionRangeRule rule;
    private Board board;

    @Before
    public void setUp() {
        ArrayList<Cell> cells = new ArrayList<>();
        this.board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        cells.add(this.board.getCell(new Coordinate(0, 0)));
        cells.add(this.board.getCell(new Coordinate(0, 1)));
        cells.add(this.board.getCell(new Coordinate(1, 0)));
        Region region1 = new Region(cells);
        this.rule = new ValidRegionRangeRule();
        this.board.addRegion(region1);
    }

    @Test
    public void setZero_ReturnsFalse() {
        Assert.assertFalse(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), 0));
    }

    @Test
    public void setOne_ReturnsTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), 1));
    }

    @Test
    public void setTwo_ReturnsTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), 2));
    }


    @Test
    public void setThree_ReturnsTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), 3));
    }

    @Test
    public void setFour_ReturnsFalse() {
        Assert.assertFalse(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), 4));
    }

}
