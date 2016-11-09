package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.AmountOfConnectionsRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by matiaskamien on 02/11/16.
 */
public class AmountOfConnectionsRuleTest {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;
    private Board board;
    private AmountOfConnectionsRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, "");
        this.rule = new AmountOfConnectionsRule();
    }

    @Test
    public void cellWithoutValueConnectWithClueWithPreviousConnection_ReturnFalse() {
        Cell cell = this.board.getCell(new Coordinate(0, 0));
        cell.addContent(new ClueContent(2));
        cell.addContent(new ValueContent(DOWN));
        Cell otherCell = this.board.getCell(new Coordinate(0, 1));
        Assert.assertTrue(!this.rule.validate(board, otherCell, LEFT));
    }

    @Test
    public void cellWithValueConnectWithClueWithPreviousConnection_ReturnFalse() {
        Cell cell = this.board.getCell(new Coordinate(0, 0));
        cell.addContent(new ClueContent(2));
        cell.addContent(new ValueContent(DOWN));
        Cell otherCell = this.board.getCell(new Coordinate(0, 1));
        otherCell.addContent(new ValueContent(DOWN));
        Assert.assertTrue(!this.rule.validate(board, otherCell, LEFT));
    }

    @Test
    public void cellWithoutValueConnectWithClueWithoutPreviousConnection_ReturnTrue() {
        Cell cell = this.board.getCell(new Coordinate(0, 0));
        cell.addContent(new ClueContent(2));
        Cell otherCell = this.board.getCell(new Coordinate(0, 1));
        Assert.assertTrue(this.rule.validate(board, otherCell, LEFT));
    }

    @Test
    public void cellWithValueConnectWithClueWithoutPreviousConnection_ReturnTrue() {
        Cell cell = this.board.getCell(new Coordinate(0, 0));
        cell.addContent(new ClueContent(2));
        Cell otherCell = this.board.getCell(new Coordinate(0, 1));
        otherCell.addContent(new ValueContent(DOWN));
        Assert.assertTrue(this.rule.validate(board, otherCell, LEFT));
    }
}
