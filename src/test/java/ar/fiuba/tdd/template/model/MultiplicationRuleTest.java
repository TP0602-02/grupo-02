package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.rules.MultiplicationRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by alazraqui on 09/10/2016.
 */
public class MultiplicationRuleTest {
    private Board board;
    private MultiplicationRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        setCol();
        setRow();
        this.rule = new MultiplicationRule();
    }

    private void setCol() {
        ArrayList<Cell> secondCol = new ArrayList<Cell>(Arrays.asList(this.board.getCell(0, 1), this.board.getCell(1, 1)));
        secondCol.add(this.board.getCell(2, 1));
        secondCol.add(this.board.getCell(3, 1));
        Region region = new Region((secondCol));
        region.setTotal(50);
        this.board.addRegion(region);
    }

    private void setRow() {
        ArrayList<Cell> secondRow = new ArrayList<Cell>(Arrays.asList(this.board.getCell(1, 0), this.board.getCell(1, 1)));
        secondRow.add(this.board.getCell(1, 2));
        secondRow.add(this.board.getCell(1, 3));
        Region region = new Region((secondRow));
        region.setTotal(36);
        this.board.addRegion(region);
    }

    @Test
    public void addInEmptyBoardReturnTrue() {
        Assert.assertTrue(rule.validate(this.board, this.board.getCell(1, 1), 5));
    }

    @Test
    public void addInEmptyBoardExcedsValueReturnFalse() {
        Assert.assertTrue(!rule.validate(this.board, this.board.getCell(1, 1), 37));
    }

    @Test
    public void addInRowWithValuesReturnTrue() {
        this.board.setValue(1, 0, new ValueContent<Integer>(2));
        Assert.assertTrue(rule.validate(this.board, this.board.getCell(1, 1), 2));
    }

    @Test
    public void addInRowWithValuesExcedReturnFalse() {
        this.board.setValue(1, 0, new ValueContent<Integer>(5));
        Assert.assertTrue(!rule.validate(this.board, this.board.getCell(1, 1), 10));
    }

    @Test
    public void addInCompleteRowIsLowerThanTotalReturnFalse() {
        this.board.setValue(1, 0, new ValueContent<Integer>(2));
        this.board.setValue(1, 2, new ValueContent<Integer>(3));
        this.board.setValue(1, 3, new ValueContent<Integer>(2));
        Assert.assertTrue(!rule.validate(this.board, this.board.getCell(1, 1), 1));
    }

    @Test
    public void addInCompleteRowReturnTrue() {
        this.board.setValue(1, 0, new ValueContent<Integer>(2));
        this.board.setValue(1, 2, new ValueContent<Integer>(1));
        this.board.setValue(1, 3, new ValueContent<Integer>(3));
        Assert.assertTrue(rule.validate(this.board, this.board.getCell(1, 1), 6));
    }

    @Test
    public void addInCompleteRowAndColumnReturnTrue() {
        this.board.setValue(1, 0, new ValueContent<Integer>(2));
        this.board.setValue(1, 2, new ValueContent<Integer>(1));
        this.board.setValue(1, 3, new ValueContent<Integer>(9));
        this.board.setValue(0, 1, new ValueContent<Integer>(5));
        this.board.setValue(2, 1, new ValueContent<Integer>(5));
        this.board.setValue(3, 1, new ValueContent<Integer>(1));
        Assert.assertTrue(rule.validate(this.board, this.board.getCell(1, 1), 2));
    }

    @Test
    public void addInCompleteRowAndColumnReturnFalseForColumn() {
        this.board.setValue(1, 0, new ValueContent<Integer>(2));
        this.board.setValue(1, 2, new ValueContent<Integer>(1));
        this.board.setValue(1, 3, new ValueContent<Integer>(9));
        this.board.setValue(0, 1, new ValueContent<Integer>(1));
        this.board.setValue(2, 1, new ValueContent<Integer>(1));
        this.board.setValue(3, 1, new ValueContent<Integer>(1));
        Assert.assertTrue(!rule.validate(this.board, this.board.getCell(1, 1), 2));
    }

}
