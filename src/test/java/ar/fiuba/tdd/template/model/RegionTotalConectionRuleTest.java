package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.RegionTotalBorderRule;
import ar.fiuba.tdd.template.rules.RegionTotalConnectionRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by alazraqui on 15/10/2016.
 */
public class RegionTotalConectionRuleTest {
    private Board board;
    private Region region;
    private RegionTotalConnectionRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, "");
        ArrayList<Cell> cells = new ArrayList<Cell>();
        cells.add(this.board.getCell(new Coordinate(0, 0)));
        cells.add(this.board.getCell(new Coordinate(1, 0)));
        cells.add(this.board.getCell(new Coordinate(1, 1)));
        this.region = new Region(cells);
        region.setClue(new ClueContent(2));
        this.board.addRegion(region);
        this.rule = new RegionTotalConnectionRule();
    }

    @Test
    public void checkEmptyBoard_ReturnFalse() {
        Assert.assertTrue(!this.rule.validate(this.board));
    }

    @Test
    public void checkwithOneCellOcuppiedWithMoreThanOneValueBoard_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 0), new ValueContent(2));
        this.board.setValue(new Coordinate(0, 0), new ValueContent(2));
        Assert.assertTrue(!this.rule.validate(this.board));
    }

    @Test
    public void checkwithTwoCellOcuppied_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 0), new ValueContent(2));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(2));
        Assert.assertTrue(this.rule.validate(this.board));
    }

    @Test
    public void checkwithTwoCellsWithMoreThanOneValue_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 0), new ValueContent(2));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(2));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(3));
        Assert.assertTrue(this.rule.validate(this.board));
    }


    @Test
    public void checkwithThreeCells_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 0), new ValueContent(2));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(2));
        this.board.setValue(new Coordinate(1, 1), new ValueContent(3));
        Assert.assertTrue(!this.rule.validate(this.board));
    }

    @Test
    public void checkWithoutRestrictions_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 0), new ValueContent(2));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(2));
        this.board.setValue(new Coordinate(1, 1), new ValueContent(3));
        this.region.setClue(new ClueContent(-1));
        Assert.assertTrue(this.rule.validate(this.board));
    }
}
