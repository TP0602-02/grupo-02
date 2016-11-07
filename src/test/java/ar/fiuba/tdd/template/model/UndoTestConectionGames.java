package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.puzzle.Puzzle;
import ar.fiuba.tdd.template.puzzle.PuzzleController;
import ar.fiuba.tdd.template.puzzle.PuzzleGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

/**
 * Created by Colo on 07/11/2016.
 */
public class UndoTestConectionGames {
    PuzzleController controller;
    Puzzle model;

    @Before
    public void setUp() {
        PuzzleGenerator puzzleGenerator = new PuzzleGenerator();
        puzzleGenerator.generatePuzzle("CountryRoad.json","COUNTRY ROAD",null,false);
        controller = puzzleGenerator.getPuzzleController();
        model = controller.getModel();
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    public void addValueAndUndoIt() {
        Cell cellToPlay = model.getCell(new Coordinate(1,1));
        controller.validateAndPlay(cellToPlay,"U");
        controller.undoPlay();
        Assert.assertTrue(cellToPlay.getContents().isEmpty());
    }

    @Test
    public void addThreeValueAndUndoThem() {
        Cell cellToPlay = model.getCell(new Coordinate(1,1));
        controller.validateAndPlay(cellToPlay,"U");
        controller.validateAndPlay(cellToPlay,"D");
        controller.undoPlay();
        String cellValue = cellToPlay.getContents().get(0).getValue();
        Assert.assertTrue(Objects.equals(cellValue,"U"));
        controller.undoPlay();
        Assert.assertTrue(cellToPlay.getContents().isEmpty());
    }

    @Test
    public void addAValueEraseItAndUndoIt() {
        Cell cellToPlay = model.getCell(new Coordinate(1,1));
        controller.validateAndPlay(cellToPlay,"5");
        controller.validateAndDelete(cellToPlay,"5");
        controller.undoPlay();
        String cellValue = cellToPlay.getContents().get(0).getValue();
        Assert.assertTrue(Objects.equals(cellValue,"5"));
    }

    @Test
    public void addMultipleValuesInDifferentCellsAndUndoThem() {
        Cell cellToPlay = model.getCell(new Coordinate(1,2));
        controller.validateAndPlay(cellToPlay,"U");
        cellToPlay = model.getCell(new Coordinate(2,1));
        controller.validateAndPlay(cellToPlay,"D");
        controller.undoPlay();
        Assert.assertTrue(cellToPlay.getContents().isEmpty());//la cell 2,1
        cellToPlay = model.getCell(new Coordinate(1,2));
        String cellValue = cellToPlay.getContents().get(0).getValue();
        Assert.assertTrue(Objects.equals(cellValue,"U"));
    }

    @Test
    public void addMultipleValuesInDifferentCellsDeleteAndUndoThem() {
        Cell cellToPlay = model.getCell(new Coordinate(1,2));
        controller.validateAndPlay(cellToPlay,"U");
        cellToPlay = model.getCell(new Coordinate(2,1));
        controller.validateAndPlay(cellToPlay,"D");
        controller.validateAndDelete(cellToPlay,"D");
        controller.undoPlay();
        String cellValue = cellToPlay.getContents().get(0).getValue();
        Assert.assertTrue(Objects.equals(cellValue,"D"));//la cell 2,1
        cellToPlay = model.getCell(new Coordinate(1,2));
        cellValue = cellToPlay.getContents().get(0).getValue();
        Assert.assertTrue(Objects.equals(cellValue,"U"));
    }
}
