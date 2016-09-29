package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.puzzle.Puzzle;
import ar.fiuba.tdd.template.puzzle.PuzzleGenerator;
import org.junit.Assert;
import org.junit.Test;

public class PuzzleTest {

    @Test
    public void generatePuzzle() {

        PuzzleGenerator puzzleGenerator = new PuzzleGenerator();

        Puzzle puzzleTest = puzzleGenerator.startGeneration();
        // Only for the Board.json
        if (puzzleTest.firstRuleIsNull()) {
            System.out.print("FIRST RULE IS NULL");
        }

        //Assert.assertTrue(puzzleTest.checkMovement(new Cell(2,3), 20));

    }
}
