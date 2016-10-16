package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.RegionTotalBorderRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by alazraqui on 15/10/2016.
 */
public class RegionTotalBorderRuleTest {
    private Board board;
    private Region region;
    private RegionTotalBorderRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, "");
        ArrayList<Cell> cell = new ArrayList<Cell>();
        cell.add(this.board.getCell(new Coordinate(0, 0)));
        this.region = new Region(cell);
        region.setTotal(2);
        this.board.addRegion(region);
        this.rule = new RegionTotalBorderRule();
    }

    @Test
    public void checkInEmptyBoard_ReturnFalse() {
        Assert.assertTrue(!this.rule.validate(this.board));
    }

    @Test
    public void checkInCellWithOneValue_ReturnFalse() {
        this.board.getCell(new Coordinate(0, 0)).setContent(new ValueContent(3));
        Assert.assertTrue(!this.rule.validate(this.board));
    }

    @Test
    public void checkInCellWithTwoValues_ReturnTrue() {
        this.board.getCell(new Coordinate(0, 0)).setContent(new ValueContent(3));
        this.board.getCell(new Coordinate(0, 0)).setContent(new ValueContent(2));
        Assert.assertTrue(this.rule.validate(this.board));
    }

    @Test
    public void checkInCellWithThreeValues_ReturnFalse() {
        this.board.getCell(new Coordinate(0, 0)).setContent(new ValueContent(3));
        this.board.getCell(new Coordinate(0, 0)).setContent(new ValueContent(2));
        this.board.getCell(new Coordinate(0, 0)).setContent(new ValueContent(1));
        Assert.assertTrue(!this.rule.validate(this.board));
    }

    @Test
    public void checkInCellWithThreeValuesInNoRestrictionRegion_ReturnTrue() {
        this.board.getCell(new Coordinate(0, 0)).setContent(new ValueContent(3));
        this.board.getCell(new Coordinate(0, 0)).setContent(new ValueContent(2));
        this.board.getCell(new Coordinate(0, 0)).setContent(new ValueContent(1));
        this.region.setTotal(-1);
        Assert.assertTrue(this.rule.validate(this.board));
    }
}