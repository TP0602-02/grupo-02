package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.RelativeClueContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.Play;
import ar.fiuba.tdd.template.rules.NumberOfDiagonalsRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class NumberOfDiagonalsRuleTest {

    private static final int upperLeftCorner = 1;
    private static final int upperRightCorner = 2;
    private static final int lowerLeftCorner = 3;
    private static final int lowerRightCorner = 4;
    private Board board;
    private NumberOfDiagonalsRule rule;
    private Region region1;
    private Region region2;

    @Before
    public void setUp() {
        this.board = new Board(6, 6, "");
        ArrayList<Cell> cells1 = new ArrayList<>();
        cells1.add(this.board.getCell(new Coordinate(0, 0)));
        cells1.add(this.board.getCell(new Coordinate(0, 1)));
        ArrayList<Cell> cells2 = new ArrayList<>();
        cells2.add(this.board.getCell(new Coordinate(0, 1)));
        cells2.add(this.board.getCell(new Coordinate(0, 2)));
        cells2.add(this.board.getCell(new Coordinate(1, 1)));
        cells2.add(this.board.getCell(new Coordinate(1, 2)));
        this.region1 = new Region(cells1);
        this.region2 = new Region(cells2);
        this.board.addRegion(region1);
        this.board.addRegion(region2);
        setClues();
        this.rule = new NumberOfDiagonalsRule();
    }

    private void setClues() {
        ClueContent firstclue = new ClueContent(2);
        ClueContent thirdclue = new ClueContent(3);
        this.region1.setClue(firstclue);
        this.region2.setClue(thirdclue);
        this.board.setValue(new Coordinate(0, 1), new RelativeClueContent(thirdclue, lowerRightCorner));
        this.board.setValue(new Coordinate(0, 2), new RelativeClueContent(thirdclue, lowerLeftCorner));
        this.board.setValue(new Coordinate(1, 1), new RelativeClueContent(thirdclue, upperRightCorner));
        this.board.setValue(new Coordinate(1, 2), new RelativeClueContent(thirdclue, upperLeftCorner));
        ClueContent fourthclue = new ClueContent(4);
        this.board.setValue(new Coordinate(1, 2), new RelativeClueContent(fourthclue, upperRightCorner));
        this.board.setValue(new Coordinate(1, 2), new RelativeClueContent(fourthclue, lowerRightCorner));
        this.board.setValue(new Coordinate(0, 1), new RelativeClueContent(firstclue, upperLeftCorner));
        this.board.setValue(new Coordinate(0, 0), new RelativeClueContent(firstclue, upperRightCorner));
        ClueContent secondclue = new ClueContent(1);
        this.board.setValue(new Coordinate(0, 1), new RelativeClueContent(secondclue, upperRightCorner));
        this.board.setValue(new Coordinate(0, 2), new RelativeClueContent(secondclue, upperLeftCorner));
    }

    @Test
    public void addFirstDiagonalNotTouchClue_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 0)), "1");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addFirstDiagonalTouchClue_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 0)), "2");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addFirstInCellWithNoRegion_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(5, 0)), "2");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addFirstDiagonalinCellWithTwoRegionsNotTouchClue_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 1)), "2");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addFirstDiagonalinCellWithTwoRegionsTouchClue_ReturnTrue() {
        Play play = new Play(this.board.getCell(new Coordinate(0, 1)), "1");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addDiagonalinCellWithOneRegionsTouchCluewithOneValue_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 1), new ValueContent("\\"));
        this.board.setValue(new Coordinate(0, 1), new ValueContent("/"));
        Play play = new Play(this.board.getCell(new Coordinate(0, 0)), "2");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addDiagonalinCellTouchCluewithThreevalues_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 1), new ValueContent("\\"));
        this.board.setValue(new Coordinate(1, 1), new ValueContent("/"));
        this.board.setValue(new Coordinate(1, 2), new ValueContent("\\"));
        Play play = new Play(this.board.getCell(new Coordinate(0, 2)), "2");
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

    @Test
    public void addDiagonalinCellTouchTwoClues_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 0), new ValueContent("/"));
        this.board.setValue(new Coordinate(1, 1), new ValueContent("/"));
        this.board.setValue(new Coordinate(1, 2), new ValueContent("\\"));
        Play play = new Play(this.board.getCell(new Coordinate(0, 1)), "1");
        Assert.assertTrue(this.rule.validate(this.board, play));
    }

    @Test
    public void addDiagonalinCellTouchTwoCluesOneFull_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 0), new ValueContent("/"));
        this.board.setValue(new Coordinate(1, 1), new ValueContent("/"));
        this.board.setValue(new Coordinate(1, 2), new ValueContent("\\"));
        this.board.setValue(new Coordinate(0, 2), new ValueContent("/"));
        Play play = new Play(this.board.getCell(new Coordinate(0, 1)), "1");
        Assert.assertFalse(this.rule.validate(this.board, play));
    }

}
