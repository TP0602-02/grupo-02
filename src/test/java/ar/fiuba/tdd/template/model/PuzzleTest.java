package ar.fiuba.tdd.template.model;

import org.junit.Test;

public class PuzzleTest {

    @Test
    public void generatePuzzle() {

/*
        PuzzleGenerator puzzleGenerator = new PuzzleGenerator();

        Puzzle puzzleTest = puzzleGenerator.startGeneration();
        // Only for the Board.json
        Assert.assertFalse(puzzleTest.checkMovement(new Cell(2, 2), 20)); // there's already a clue there
        Assert.assertTrue(puzzleTest.checkMovement(new Cell(1, 2), 4)); // there are no 4s in row or col
        Assert.assertTrue(puzzleTest.checkMovement(new Cell(2, 0), 3));
*/
    }

    @Test
    public void validatePlays() {

        // TODO: Fix this
      /*  PuzzleGenerator puzzleGenerator = new PuzzleGenerator();

        Puzzle puzzleTest = puzzleGenerator.startGeneration();
        Parser parser = new Parser();
        parser.decodeJson();
        ArrayList<Play> playResults = new ArrayList<>();
        for ( Cell selectedCell : parser.getPlays()) {
            Play play = new Play(selectedCell);
            // Checks valid plays
            boolean validPlay = puzzleTest.checkMovement(play);
            play.setValidPlay(validPlay);
            playResults.add(play);
            Assert.assertTrue(puzzleTest.checkMovement(play));
        }
        parser.writePlayResults(playResults);*/


    }
}
