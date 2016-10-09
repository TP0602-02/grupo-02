package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.Parser;
import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.puzzle.Puzzle;
import ar.fiuba.tdd.template.puzzle.PuzzleGenerator;
import org.junit.Assert;
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
        PuzzleGenerator puzzleGenerator = new PuzzleGenerator();

        Puzzle puzzleTest = puzzleGenerator.startGeneration();
        Parser parser = new Parser();
        parser.decodeJson();
        for ( Cell selectedCell : parser.getPlays()) {
            Play play = new Play(selectedCell);
            // Checks valid plays
            Assert.assertTrue(puzzleTest.checkMovement(play));
        }


    }
}
