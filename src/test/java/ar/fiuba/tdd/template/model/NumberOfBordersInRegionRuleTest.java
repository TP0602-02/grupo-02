package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.NumberOfBordersInRegionRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


/**
 * Created by alazraqui on 15/10/2016.
 */
public class NumberOfBordersInRegionRuleTest {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;
    private Board board;
    private Region region;
    private Cell cell;
    private NumberOfBordersInRegionRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, "");
        ArrayList<Cell> cells = new ArrayList<Cell>();
        cells.add(this.board.getCell(new Coordinate(0, 0)));
        this.cell = this.board.getCell(new Coordinate(0, 0));
        this.region = new Region(cells);
        this.board.addRegion(this.region);
        this.rule = new NumberOfBordersInRegionRule();
    }

    @Test
    public void addValueInRegionWithoutRestrictions_ReturnTrue() {
        this.cell.setContent(new ValueContent(LEFT));
        Assert.assertTrue(this.rule.validate(this.board, this.cell, UP));
    }

    @Test
    public void addValueInRegionInLimitOfRestriction_ReturnTrue() {
        this.cell.setContent(new ValueContent(LEFT));
        this.region.setClue(new ClueContent(2));
        Assert.assertTrue(this.rule.validate(this.board, this.cell, UP));
    }

    @Test
    public void addValueInRegionExcedsLimit_ReturnTrue() {
        this.cell.setContent(new ValueContent(LEFT));
        this.region.setClue(new ClueContent(1));
        Assert.assertTrue(!this.rule.validate(this.board, this.cell, UP));
    }
}
