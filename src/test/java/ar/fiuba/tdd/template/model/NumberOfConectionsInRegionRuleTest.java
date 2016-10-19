package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.NumberOfConectionsInRegionRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by alazraqui on 13/10/2016.
 */
public class NumberOfConectionsInRegionRuleTest {
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;

    private Board board;
    private Region region;
    private NumberOfConectionsInRegionRule rule;


    @Before
    public void setUp() {
        this.board = new Board(9, 9, "");
        ArrayList<Cell> cells = new ArrayList<Cell>();
        cells.add(this.board.getCell(new Coordinate(0, 0)));
        cells.add(this.board.getCell(new Coordinate(0, 1)));
        cells.add(this.board.getCell(new Coordinate(0, 2)));
        cells.add(this.board.getCell(new Coordinate(1, 0)));
        cells.add(this.board.getCell(new Coordinate(1, 1)));
        cells.add(this.board.getCell(new Coordinate(1, 2)));
        cells.add(this.board.getCell(new Coordinate(2, 0)));
        cells.add(this.board.getCell(new Coordinate(2, 1)));
        cells.add(this.board.getCell(new Coordinate(2, 2)));
        this.region = new Region(cells);
        this.region.setClue(new ClueContent(3));
        this.board.addRegion(region);
        this.rule = new NumberOfConectionsInRegionRule();
    }

    @Test
    public void addFirstConectionInRegion_ReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), RIGHT));
    }

    @Test
    public void addConectionInSameRegionWithOneValue_ReturnTrue() {
        this.board.setValue(new Coordinate(2, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(LEFT));
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), RIGHT));
    }

    @Test
    public void addConectionInSameRegionWithTwoValues_ReturnFalse() {
        this.board.setValue(new Coordinate(2, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(LEFT));
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(0, 0)), RIGHT));
    }

    @Test
    public void addConectionInOtherRegionWithTwoValues_ReturnTrue() {
        this.board.setValue(new Coordinate(2, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(LEFT));
        ArrayList<Cell> cells = new ArrayList<Cell>();
        cells.add(this.board.getCell(new Coordinate(3, 0)));
        this.board.addRegion(new Region(cells));
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(2, 0)), DOWN));
    }

    @Test
    public void addConectionInOtherRegionWithThreeValuesButTwoCells_ReturnTrue() {
        this.board.setValue(new Coordinate(2, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(2, 2), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(3, 2), new ValueContent(UP));
        ArrayList<Cell> cells = new ArrayList<Cell>();
        cells.add(this.board.getCell(new Coordinate(3, 0)));
        this.board.addRegion(new Region(cells));
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(new Coordinate(2, 0)), DOWN));
    }

    @Test
    public void addConectionInOtherRegionWithThreeValues_ReturnTrue() {
        this.board.setValue(new Coordinate(2, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(0, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(0, 3), new ValueContent(LEFT));
        ArrayList<Cell> cells = new ArrayList<Cell>();
        cells.add(this.board.getCell(new Coordinate(3, 0)));
        this.board.addRegion(new Region(cells));
        Assert.assertTrue(!this.rule.validate(this.board, this.board.getCell(new Coordinate(2, 0)), DOWN));
    }


}
