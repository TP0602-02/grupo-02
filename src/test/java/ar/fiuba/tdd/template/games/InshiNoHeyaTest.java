package ar.fiuba.tdd.template.games;

import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.puzzle.Puzzle;

import ar.fiuba.tdd.template.puzzle.PuzzleGenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class InshiNoHeyaTest {

    private static final String INSHI_FILE = "InshiNoHeya.json";
    private static final String INSHI_PLAYS_FILE = "InshiNoHeyaPlaysAc2.json";

    private Puzzle puzzle;

    @Before
    public void setUp() {
        PuzzleGenerator puzzleGenerator = new PuzzleGenerator();
        puzzleGenerator.getParser().decodeJson(INSHI_FILE, INSHI_PLAYS_FILE);
        puzzleGenerator.createGame("Inshi", false);

        puzzleGenerator.getPuzzleController().execPlays(puzzleGenerator.getParser().getPlays());
        this.puzzle = puzzleGenerator.getPuzzleController().getModel();
    }

    @Test
    public void boardIsFull() {
        Assert.assertTrue(puzzle.getBoard().isFull());
    }

    @Test
    public void boardHasCorrectPlayValues() {
        ArrayList<CellContent> valuesRecovered = puzzle.getBoard().getContents(new Coordinate(0, 0));
        Assert.assertEquals(3, valuesRecovered.get(0).getNumberValue());
        valuesRecovered = puzzle.getBoard().getContents(new Coordinate(3, 2));
        Assert.assertEquals(4, valuesRecovered.get(0).getNumberValue());
        valuesRecovered = puzzle.getBoard().getContents(new Coordinate(4, 4));
        Assert.assertEquals(1, valuesRecovered.get(0).getNumberValue());
    }

}
