package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Parser;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.RulesFactory;

import java.util.ArrayList;

public class PuzzleGenerator {

    public PuzzleGenerator() {}

    public Puzzle startGeneration() {

        Parser parser = new Parser();
        parser.decodeJson();
        int width = parser.getWidth();
        int height = parser.getHeight();
        ArrayList<Cell> clues = parser.getClues();
//        ArrayList<Cell> solution = parser.getSolution();
        ArrayList<String> rules = parser.getRules();

        // Converts rules array of strings into GenericRule array

        ArrayList<GenericRule> parsedRules = new ArrayList<>();
        for (int i = 0; i < rules.size(); i++) {
            parsedRules.add(RulesFactory.getFactory().createRule(rules.get(i)));
        }


        //must merge with develop
        Puzzle puzzle = new Puzzle(width, height, createChainOfRules(parsedRules), clues);

        return puzzle;
    }

    public GenericRule createChainOfRules(ArrayList<GenericRule> parsedRules) {
        int indexOfFirstRule = 0;
        for (int index = 0; index < parsedRules.size() - 1 ; ++index ) {
            parsedRules.get(index).setNextRule(parsedRules.get(index + 1));
        }
        return parsedRules.get(indexOfFirstRule);
    }
}
