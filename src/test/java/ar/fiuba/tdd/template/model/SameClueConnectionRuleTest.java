package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.rules.SameClueConnectionRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SameClueConnectionRuleTest {
    private static final int UP = 3;
    private static final int DOWN = 4;
    private Board board;
    private SameClueConnectionRule rule;

    @Before
    public void setUp() {
        this.board = new Board(4, 4, "");
        this.rule = new SameClueConnectionRule();
    }

    @Test
    public void addValueInNormalCellWithSameClue_ReturnTrue() {
        Cell clueCell = this.board.getCell(new Coordinate(0,1));
        clueCell.getContents().add(new ClueContent(2));

        Cell otherClueCell = this.board.getCell(new Coordinate(3,1));
        otherClueCell.getContents().add(new ClueContent(2));
        otherClueCell.getContents().add(new ValueContent(UP));

        Cell firstCell = this.board.getCell(new Coordinate(2,1));
        firstCell.getContents().add(new ValueContent(DOWN));
        firstCell.getContents().add(new ValueContent(UP));

        Cell secondCell = this.board.getCell(new Coordinate(1,1));
        secondCell.getContents().add(new ValueContent(DOWN));
        Assert.assertTrue(this.rule.validate(board, secondCell, UP));
    }

    @Test
    public void addValueInNormalCellWithDifferentClue_ReturnFalse() {
        Cell clueCell = this.board.getCell(new Coordinate(0,1));
        clueCell.getContents().add(new ClueContent(2));

        Cell otherClueCell = this.board.getCell(new Coordinate(3,1));
        otherClueCell.getContents().add(new ClueContent(3));
        otherClueCell.getContents().add(new ValueContent(UP));

        Cell firstCell = this.board.getCell(new Coordinate(2,1));
        firstCell.getContents().add(new ValueContent(DOWN));
        firstCell.getContents().add(new ValueContent(UP));

        Cell secondCell = this.board.getCell(new Coordinate(1,1));
        secondCell.getContents().add(new ValueContent(DOWN));
        Assert.assertFalse(this.rule.validate(board, secondCell, UP));
    }

    @Test
    public void addValueInClueCellWithSameClue_ReturnTrue() {
        Cell clueCell = this.board.getCell(new Coordinate(0,1));
        clueCell.getContents().add(new ClueContent(2));

        Cell otherClueCell = this.board.getCell(new Coordinate(3,1));
        otherClueCell.getContents().add(new ClueContent(2));
        otherClueCell.getContents().add(new ValueContent(UP));

        Cell firstCell = this.board.getCell(new Coordinate(2,1));
        firstCell.getContents().add(new ValueContent(DOWN));
        firstCell.getContents().add(new ValueContent(UP));

        Cell secondCell = this.board.getCell(new Coordinate(1,1));
        secondCell.getContents().add(new ValueContent(DOWN));
        Assert.assertTrue(this.rule.validate(board, clueCell, DOWN));
    }

    @Test
    public void addValueInClueCellWithDifferentClue_ReturnFalse() {
        Cell clueCell = this.board.getCell(new Coordinate(0,1));
        clueCell.getContents().add(new ClueContent(2));

        Cell otherClueCell = this.board.getCell(new Coordinate(3,1));
        otherClueCell.getContents().add(new ClueContent(3));
        otherClueCell.getContents().add(new ValueContent(UP));

        Cell firstCell = this.board.getCell(new Coordinate(2,1));
        firstCell.getContents().add(new ValueContent(DOWN));
        firstCell.getContents().add(new ValueContent(UP));

        Cell secondCell = this.board.getCell(new Coordinate(1,1));
        secondCell.getContents().add(new ValueContent(DOWN));
        Assert.assertFalse(this.rule.validate(board, clueCell, DOWN));
    }
}
