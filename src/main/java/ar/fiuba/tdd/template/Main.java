package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.board.cell.model.BlackContent;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.puzzle.Puzzle;
import ar.fiuba.tdd.template.puzzle.PuzzleController;
import ar.fiuba.tdd.template.puzzle.PuzzleGenerator;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.RulesFactory;
import ar.fiuba.tdd.template.userinterface.controller.Facade;
import ar.fiuba.tdd.template.userinterface.view.PuzzleView;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PuzzleGenerator puzzleGenerator = new PuzzleGenerator();
        puzzleGenerator.runGeneration();
    }
}
