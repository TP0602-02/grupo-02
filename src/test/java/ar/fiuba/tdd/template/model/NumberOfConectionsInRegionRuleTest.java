package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.Play;
import ar.fiuba.tdd.template.rules.NumberOfConectionsInRegionRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class NumberOfConectionsInRegionRuleTest {
    private static final String LEFT = "1";
    private static final String RIGHT = "2";
    private static final String UP = "3";
    private static final String DOWN = "4";

    private Board board;
    private NumberOfConectionsInRegionRule rule;

    @Before
    public void setUp() {
        this.board = new Board(9, 9, "");
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(this.board.getCell(new Coordinate(0, 0)));
        cells.add(this.board.getCell(new Coordinate(0, 1)));
        cells.add(this.board.getCell(new Coordinate(0, 2)));
        cells.add(this.board.getCell(new Coordinate(1, 0)));
        cells.add(this.board.getCell(new Coordinate(1, 1)));
        cells.add(this.board.getCell(new Coordinate(1, 2)));
        cells.add(this.board.getCell(new Coordinate(2, 0)));
        cells.add(this.board.getCell(new Coordinate(2, 1)));
        cells.add(this.board.getCell(new Coordinate(2, 2)));
        Region region = new Region(cells);
        region.setClue(new ClueContent(3));
        this.board.addRegion(region);
        this.rule = new NumberOfConectionsInRegionRule();
    }

    @Test
    public void addFirstConectionInRegion_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 0)), RIGHT);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addConectionInSameRegionWithOneValue_ReturnTrue() {
        this.board.setValue(new Coordinate(2, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(LEFT));
        Play play = new Play(this.board.getCell(new Coordinate(0, 0)), RIGHT);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addConectionInSameRegionWithTwoValues_ReturnFalse() {
        this.board.setValue(new Coordinate(2, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(LEFT));
        Play play = new Play(this.board.getCell(new Coordinate(0, 0)), RIGHT);
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void addConectionInOtherRegionWithTwoValues_ReturnTrue() {
        this.board.setValue(new Coordinate(2, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(LEFT));
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(this.board.getCell(new Coordinate(3, 0)));
        this.board.addRegion(new Region(cells));
        Play play = new Play(this.board.getCell(new Coordinate(2, 0)), DOWN);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addConectionInOtherRegionWithThreeValuesButTwoCells_ReturnTrue() {
        this.board.setValue(new Coordinate(2, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(2, 2), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(3, 2), new ValueContent(UP));
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(this.board.getCell(new Coordinate(3, 0)));
        this.board.addRegion(new Region(cells));
        Play play = new Play(this.board.getCell(new Coordinate(2, 0)), DOWN);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addConectionInOtherRegionWithThreeValues_ReturnTrue() {
        this.board.setValue(new Coordinate(2, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(0, 2), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(0, 3), new ValueContent(LEFT));
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(this.board.getCell(new Coordinate(3, 0)));
        this.board.addRegion(new Region(cells));
        Play play = new Play(this.board.getCell(new Coordinate(2, 0)), DOWN);
        Assert.assertTrue(!this.rule.validate(this.board, play));
    }

}
