package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.Play;
import ar.fiuba.tdd.template.rules.RegionHasValidConectionsRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class RegionHasValidConectionsRuleTest {
    private static final String LEFT = "1";
    private static final String RIGHT = "2";
    private static final String UP = "3";
    private static final String DOWN = "4";

    private Board board;
    private RegionHasValidConectionsRule rule;

    @Before
    public void setUp() {
        this.board = new Board(9, 9, "");
        ArrayList<Cell> cellsOne = new ArrayList<>();
        cellsOne.add(this.board.getCell(new Coordinate(0, 0)));
        cellsOne.add(this.board.getCell(new Coordinate(0, 1)));
        cellsOne.add(this.board.getCell(new Coordinate(0, 2)));
        cellsOne.add(this.board.getCell(new Coordinate(1, 0)));
        ArrayList<Cell> cellsTwo = new ArrayList<>();
        cellsTwo.add(this.board.getCell(new Coordinate(1, 1)));
        cellsTwo.add(this.board.getCell(new Coordinate(2, 0)));
        cellsTwo.add(this.board.getCell(new Coordinate(2, 1)));
        cellsTwo.add(this.board.getCell(new Coordinate(2, 2)));
        cellsTwo.add(this.board.getCell(new Coordinate(2, 3)));
        ArrayList<Cell> cellsThree = new ArrayList<>();
        cellsThree.add(this.board.getCell(new Coordinate(3, 3)));
        cellsThree.add(this.board.getCell(new Coordinate(3, 2)));
        this.board.addRegion(new Region(cellsOne));
        this.board.addRegion(new Region(cellsTwo));
        this.board.addRegion(new Region(cellsThree));
        this.rule = new RegionHasValidConectionsRule();
    }

    @Test
    public void insertFirstConnectionInRegion_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 0)), RIGHT);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void insertInRegionWithTwoConnections_ReturnFalse() {
        this.board.setValue(new Coordinate(1, 0), new ValueContent(RIGHT));
        this.board.setValue(new Coordinate(1, 1), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(2, 2), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(3, 2), new ValueContent(UP));
        Play play = new Play(this.board.getCell(new Coordinate(2, 0)), UP);
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void insertInSameRegionWithOneConnection_ReturnTrue() {
        this.board.setValue(new Coordinate(1, 1), new ValueContent(LEFT));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(RIGHT));
        Play play = new Play(this.board.getCell(new Coordinate(0, 0)), RIGHT);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void insertInRegionWithFullConnections_ReturnFalse() {
        this.board.setValue(new Coordinate(3, 3), new ValueContent(UP));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(3, 2), new ValueContent(UP));
        this.board.setValue(new Coordinate(2, 2), new ValueContent(DOWN));
        Play play = new Play(this.board.getCell(new Coordinate(1, 0)), RIGHT);
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void insertInSameRegionWithFullConnections_ReturnTrue() {
        this.board.setValue(new Coordinate(3, 3), new ValueContent(UP));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(DOWN));
        this.board.setValue(new Coordinate(3, 2), new ValueContent(UP));
        this.board.setValue(new Coordinate(2, 2), new ValueContent(DOWN));
        Play play = new Play(this.board.getCell(new Coordinate(2, 2)), LEFT);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void insertRegionWithNoConectionWithRegionWithOneConnection_ReturnTrue() {
        this.board.setValue(new Coordinate(3, 3), new ValueContent(UP));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(DOWN));
        Play play = new Play(this.board.getCell(new Coordinate(1, 0)), RIGHT);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void insertRegionWithOneConectionWithRegionWithNoConnection_ReturnTrue() {
        this.board.setValue(new Coordinate(3, 3), new ValueContent(UP));
        this.board.setValue(new Coordinate(2, 3), new ValueContent(DOWN));
        Play play = new Play(this.board.getCell(new Coordinate(1, 1)), LEFT);
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

}
