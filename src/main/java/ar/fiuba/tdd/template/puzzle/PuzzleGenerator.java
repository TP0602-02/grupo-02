package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Parser;
import ar.fiuba.tdd.template.board.InputUserView;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.RulesFactory;

import java.util.ArrayList;

public class PuzzleGenerator {

    public PuzzleGenerator() {}

    public Puzzle startGeneration() {

        Parser parser = new Parser();
        parser.decodeJson();

        ArrayList<Cell> clues = parser.getClues();
        //ArrayList<Cell> solution = parser.getSolution();
        ArrayList<String> rules = parser.getRules();

        // Converts rules array of strings into GenericRule array
        ArrayList<GenericRule> parsedRules = new ArrayList<>();
        for (String rule : rules) {
            parsedRules.add(RulesFactory.getFactory().createRule(rule));
        }

        initEnabledButtonsToPlay();
        return new Puzzle(parser.getHeight(), parser.getWidth(), parsedRules, clues);
    }

    private void initEnabledButtonsToPlay() {
        //TODO debe ser levantado del archivo con el parser
        ArrayList<String> enabledValues = new ArrayList<>();
        enabledValues.add("1");
        enabledValues.add("2");
        enabledValues.add("3");
        enabledValues.add("4");
        enabledValues.add("5");
        enabledValues.add("6");
        enabledValues.add("7");
        enabledValues.add("8");
        enabledValues.add("9");
        enabledValues.add("-");
        InputUserView.createView(enabledValues);
    }

}
