package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.SumRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by matiaskamien on 08/10/16.
 */
public class SumRuleTest {
    private Board board;
    private SumRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        setCol();
        setRow();
        this.rule = new SumRule();
    }

    private void setCol() {
        ArrayList<Cell> secondCol = new ArrayList<Cell>(Arrays.asList(
                this.board.getCell(new Coordinate(0, 1)), this.board.getCell(new Coordinate(1, 1))));
        secondCol.add(this.board.getCell(new Coordinate(2, 1)));
        secondCol.add(this.board.getCell(new Coordinate(3, 1)));
        Region region = new Region((secondCol));
        region.setTotal(6);
        this.board.addRegion(region);
    }

    private void setRow() {
        ArrayList<Cell> secondRow = new ArrayList<Cell>(Arrays.asList(
                this.board.getCell(new Coordinate(1, 0)), this.board.getCell(new Coordinate(1, 1))));
        secondRow.add(this.board.getCell(new Coordinate(1, 2)));
        secondRow.add(this.board.getCell(new Coordinate(1, 3)));
        Region region = new Region((secondRow));
        region.setTotal(5);
        this.board.addRegion(region);
    }

    @Test
    public void addInEmptyBoardReturnTrue() {
        Assert.assertTrue(rule.validate(this.board, this.board.getCell(new Coordinate(1, 1)), 5));
    }

    @Test
    public void addInEmptyBoardExcedsValueReturnFalse() {
        Assert.assertTrue(!rule.validate(this.board, this.board.getCell(new Coordinate(1, 1)), 8));
    }

    @Test
    public void addInEmptyBoardExcedsOneValueReturnFalse() {
        Assert.assertTrue(!rule.validate(this.board, this.board.getCell(new Coordinate(1, 1)), 6));
    }

    @Test
    public void addInRowWithValuesReturnTrue() {
        this.board.setValue(new Coordinate(1, 0), new ValueContent(2));
        Assert.assertTrue(rule.validate(this.board, this.board.getCell(new Coordinate(1, 1)), 2));
    }

    @Test
    public void addInRowWithValuesExcedReturnFalse() {
        this.board.setValue(new Coordinate(1, 0), new ValueContent(5));
        Assert.assertTrue(!rule.validate(this.board, this.board.getCell(new Coordinate(1, 1)), 2));
    }

    @Test
    public void addInCompleteRowIsLowerThanTotalReturnFalse() {
        this.board.setValue(new Coordinate(1, 0), new ValueContent(1));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(1));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(1));
        Assert.assertTrue(!rule.validate(this.board, this.board.getCell(new Coordinate(1, 1)), 1));
    }

    @Test
    public void addInCompleteRowReturnTrue() {
        this.board.setValue(new Coordinate(1, 0), new ValueContent(1));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(1));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(1));
        Assert.assertTrue(rule.validate(this.board, this.board.getCell(new Coordinate(1, 1)), 2));
    }

    @Test
    public void addInCompleteRowAndColumnReturnTrue() {
        this.board.setValue(new Coordinate(1, 0), new ValueContent(1));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(1));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(1));
        this.board.setValue(new Coordinate(0, 1), new ValueContent(2));
        this.board.setValue(new Coordinate(2, 1), new ValueContent(1));
        this.board.setValue(new Coordinate(3, 1), new ValueContent(1));
        Assert.assertTrue(rule.validate(this.board, this.board.getCell(new Coordinate(1, 1)), 2));
    }

    @Test
    public void addInCompleteRowAndColumnReturnFalseForColumn() {
        this.board.setValue(new Coordinate(1, 0), new ValueContent(1));
        this.board.setValue(new Coordinate(1, 2), new ValueContent(1));
        this.board.setValue(new Coordinate(1, 3), new ValueContent(1));
        this.board.setValue(new Coordinate(0, 1), new ValueContent(1));
        this.board.setValue(new Coordinate(2, 1), new ValueContent(1));
        this.board.setValue(new Coordinate(3, 1), new ValueContent(1));
        Assert.assertTrue(!rule.validate(this.board, this.board.getCell(new Coordinate(1, 1)), 2));
    }
}

