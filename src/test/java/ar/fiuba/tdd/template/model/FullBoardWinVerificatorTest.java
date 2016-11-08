package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.BlackContent;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.winverificators.FullBoardWinVerificator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FullBoardWinVerificatorTest {
    private Board board;
    private FullBoardWinVerificator winVerificator;

    @Before
    public void setUp() {
        this.board = new Board(2, 2, "");
        this.winVerificator = new FullBoardWinVerificator();
    }

    @Test
    public void emptyBoard_ReturnFalse() {
        Assert.assertFalse(winVerificator.wonTheGame(this.board));
    }

    @Test
    public void boardWithValues_ReturnFalse() {
        this.board.setValue(new Coordinate(0, 0), new BlackContent());
        this.board.setValue(new Coordinate(0, 0), new ClueContent(1));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(1));
        this.board.setValue(new Coordinate(1, 1), new ValueContent(2));
        Assert.assertFalse(winVerificator.wonTheGame(this.board));
    }

    @Test
    public void boardWithValues_ReturnTrue() {
        this.board.setValue(new Coordinate(0, 0), new BlackContent());
        this.board.setValue(new Coordinate(0, 0), new ClueContent(1));
        this.board.setValue(new Coordinate(1, 0), new ValueContent(1));
        this.board.setValue(new Coordinate(0, 1), new ValueContent(3));
        this.board.setValue(new Coordinate(1, 1), new ValueContent(2));
        Assert.assertTrue(winVerificator.wonTheGame(this.board));
    }
}
