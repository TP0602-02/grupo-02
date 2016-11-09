package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.puzzle.controller.PuzzleGenerator;

public class Main {
    public static void main(String[] args) {
        PuzzleGenerator puzzleGenerator = new PuzzleGenerator();
        puzzleGenerator.runGeneration();
    }
}
