package ar.fiuba.tdd.template.games;

import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.puzzle.Puzzle;

import ar.fiuba.tdd.template.puzzle.PuzzleGenerator;

import ar.fiuba.tdd.template.winverificators.CloseCircuitVerificator;
import ar.fiuba.tdd.template.winverificators.WinVerificator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CountryRoadTest {

    private static final String COUNTRYROAD_FILE = "CountryRoadAc2.json";
    private static final String COUNTRYROAD_PLAYS_FILE = "CountryRoadPlaysAc2.json";

    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;

    private Puzzle puzzle;

    @Before
    public void setUp() {
        System.setProperty("java.awt.headless", "false");
        PuzzleGenerator puzzleGenerator = new PuzzleGenerator();
        puzzleGenerator.getParser().decodeJson(COUNTRYROAD_FILE, COUNTRYROAD_PLAYS_FILE);
        puzzleGenerator.createGame("Country Road", false);

        puzzleGenerator.getPuzzleController().execPlays(puzzleGenerator.getParser().getPlays());
        this.puzzle = puzzleGenerator.getPuzzleController().getModel();
    }

    @Test
    public void boardHasCorrectPlayValues() {
        ArrayList<CellContent> valuesRecovered = puzzle.getBoard().getContents(new Coordinate(1, 1));
        ArrayList<Integer> integerContents = new ArrayList<>();
        for (CellContent cellContent : valuesRecovered) {
            integerContents.add(cellContent.getNumberValue());
        }

        // According to COUNTRYROAD_PLAYS_FILE, 1-1 (2-2 in the file) must contain UP and LEFT
        Assert.assertTrue(integerContents.contains(UP));
        Assert.assertTrue(integerContents.contains(LEFT));
        Assert.assertFalse(integerContents.contains(RIGHT));
        Assert.assertFalse(integerContents.contains(DOWN));

    }


    @Test
    public void winVerificatorWinsGame() {
        WinVerificator winVerificator = new CloseCircuitVerificator();

        Assert.assertTrue(winVerificator.wonTheGame(puzzle.getBoard()));

    }

}
