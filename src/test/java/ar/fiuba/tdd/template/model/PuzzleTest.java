package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.Parser;
import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.entity.Coordinate;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

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

   /* @Test
    public void writingFileTest() {
        Board board = new Board(4, 4, CellFactory.CELL_SINGLE_VALUE);
        Parser parser = new Parser();
        ArrayList<Play> playResults = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            board.setValue(new Coordinate(i, i), new ValueContent(2));
            Play play = new Play(board.getCell(new Coordinate(i, i)));
            if (i == 2) {
                play.setValidPlay(true);
            } else {
                play.setValidPlay(false);
            }
            playResults.add(play);
        }
        parser.writePlayResults(playResults, "src/json/PlayOutputTest.json");
    }*/


}
