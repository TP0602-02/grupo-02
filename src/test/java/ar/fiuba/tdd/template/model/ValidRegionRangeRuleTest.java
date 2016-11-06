package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
import ar.fiuba.tdd.template.board.cell.model.CellSingleValue;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.ValidRegionRangeRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by alazraqui on 02/11/2016.
 */
public class ValidRegionRangeRuleTest {
    private Region region1;
    private ValidRegionRangeRule rule;
    private Board board;

    @Before
    public void setUp() {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        this.board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        cells.add(this.board.getCell(new Coordinate(0, 0)));
        cells.add(this.board.getCell(new Coordinate(0, 1)));
        cells.add(this.board.getCell(new Coordinate(1, 0)));
        this.region1 = new Region(cells);
        this.rule = new ValidRegionRangeRule();

        this.board.addRegion(region1);
    }

    @Test
    public void setZero_ReturnsFalse() {
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), 0));
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
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), 4));
    }

}
