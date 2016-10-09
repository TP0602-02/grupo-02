package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.*;
import ar.fiuba.tdd.template.rules.NoRepeatValueRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by alazraqui on 08/10/2016.
 */
public class NoRepeatValueRuleTest {
    private Board board;
    private NoRepeatValueRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        setCols();
        setRows();
        this.rule = new NoRepeatValueRule();
    }

    private void setCols() {
        ArrayList<Cell> firstCol = new ArrayList<Cell>(Arrays.asList(this.board.getCell(0, 0), this.board.getCell(1, 0)));
        firstCol.add(board.getCell(2,0));
        firstCol.add(this.board.getCell(3, 0));
        ArrayList<Cell> secondCol = new ArrayList<Cell>(Arrays.asList(this.board.getCell(0, 1), this.board.getCell(1, 1)));
        secondCol.add(this.board.getCell(2,1));
        secondCol.add(this.board.getCell(3, 1));
        ArrayList<Cell> thirdCol = new ArrayList<Cell>(Arrays.asList(this.board.getCell(0, 2), this.board.getCell(1, 2)));
        thirdCol.add(this.board.getCell(2,2));
        thirdCol.add(board.getCell(3, 2));
        ArrayList<Cell> fourthCol = new ArrayList<Cell>(Arrays.asList(this.board.getCell(0, 3), this.board.getCell(1, 3)));
        fourthCol.add(this.board.getCell(2, 3));
        fourthCol.add(this.board.getCell(3, 3));
        this.board.addRegion(new Region(firstCol));
        this.board.addRegion(new Region(secondCol));
        this.board.addRegion(new Region(thirdCol));
        this.board.addRegion(new Region(fourthCol));
    }

    private void setRows() {
        ArrayList<Cell> firstRow = new ArrayList<Cell>(Arrays.asList(this.board.getCell(0, 0), this.board.getCell(0, 1)));
        firstRow.add(this.board.getCell(0, 2));
        firstRow.add(this.board.getCell(0, 3));
        ArrayList<Cell> secondRow = new ArrayList<Cell>(Arrays.asList(this.board.getCell(1, 0), this.board.getCell(1, 1)));
        secondRow.add(this.board.getCell(1, 2));
        secondRow.add(this.board.getCell(1, 3));
        ArrayList<Cell> thirdRow = new ArrayList<Cell>(Arrays.asList(this.board.getCell(2, 0), this.board.getCell(2, 1)));
        thirdRow.add(board.getCell(2, 2));
        thirdRow.add(this.board.getCell(2, 3));
        ArrayList<Cell> fourthRow = new ArrayList<Cell>(Arrays.asList(this.board.getCell(3, 0), this.board.getCell(3, 1)));
        fourthRow.add(this.board.getCell(3, 2));
        fourthRow.add(this.board.getCell(3, 3));

        this.board.addRegion(new Region(firstRow));
        this.board.addRegion(new Region(secondRow));
        this.board.addRegion(new Region(thirdRow));
        this.board.addRegion(new Region(fourthRow));
    }

    @Test
    public void addInEmptyBoardReturnTrue() {
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(0, 0), 3));
    }

    @Test
    public void addInTheSameCellReturnTrue() {
        this.board.setValue(0, 0, new ValueContent<Integer>(1));
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(0, 0), 1));
    }

    @Test
    public void addNumberInRowReturnFalse() {
        this.board.setValue(0, 0, new ValueContent<Integer>(1));
        this.board.setValue(0, 3, new ValueContent<Integer>(2));
        Assert.assertFalse(this.rule.validate(this.board, this.board.getCell(0, 2), 1));
    }


    @Test
    public void addNumberIsValidForRowButNotForColumn() {
        this.board.setValue(0, 0, new ValueContent<Integer>(1));
        this.board.setValue(0, 3, new ValueContent<Integer>(2));
        this.board.setValue(1, 2, new ValueContent<Integer>(3));
        Assert.assertFalse(this.rule.validate(this.board, this.board.getCell(0, 2), 3));
    }

    @Test
    public void addNumberCompleteColumnAndRow() {
        this.board.setValue(0, 1, new ValueContent<Integer>(1));
        this.board.setValue(0, 2, new ValueContent<Integer>(2));
        this.board.setValue(0, 3, new ValueContent<Integer>(3));
        this.board.setValue(1, 0, new ValueContent<Integer>(3));
        this.board.setValue(2, 0, new ValueContent<Integer>(2));
        this.board.setValue(3, 0, new ValueContent<Integer>(3));
        Assert.assertTrue(this.rule.validate(this.board, this.board.getCell(0, 0), 4));
    }


}
