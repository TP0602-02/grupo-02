package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Parser;
import ar.fiuba.tdd.template.board.InputUserView;
import ar.fiuba.tdd.template.board.cell.RegionJson;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.RulesFactory;
import ar.fiuba.tdd.template.userinterface.view.PuzzleView;
import ar.fiuba.tdd.template.userinterface.view.StartView;
import ar.fiuba.tdd.template.winverificators.WinVerificator;
import ar.fiuba.tdd.template.winverificators.WinVerificatorFactory;

import java.util.ArrayList;

public class PuzzleGenerator {
    private Parser parser;

    public void runGeneration() {
        StartView startView = new StartView(new StartView.StartGameListener() {
            @Override
            public void loadNewGame(String gameName, String gameFile) {
                Puzzle puzzle = startGeneration(gameFile);
                PuzzleView puzzleView = new PuzzleView(puzzle.getBoardHeight(), puzzle.getBoardWidth(),
                        puzzle.getInitialCells(), gameName);
                puzzleView.showVisu();

                ArrayList<String> winVerificators = parser.getWinVerificators();
                // Converts win verificator array of strings into WinVerificator array
                ArrayList<WinVerificator> parsedWinVerificators = new ArrayList<>();
                for (String verificator : winVerificators) {
                    parsedWinVerificators.add(WinVerificatorFactory.getFactory().createVerificator(verificator));
                }

                PuzzleController puzzleController = new PuzzleController(parsedWinVerificators);
                //TODO se debe levantar del archivo el booleano que se le pasa
                puzzleController.setAddWithConnections(false);
                puzzleController.attachElements(puzzleView, puzzle);
            }
        });
        startView.start();
    }

    public PuzzleGenerator() {
        parser = new Parser();
    }

    private Puzzle startGeneration(String fileName) {
        parser.decodeJson(fileName, null);
        //  parser.decodeJson(fileName,"Plays.json");

        ArrayList<String> rules = parser.getRules();

        // Converts rules array of strings into GenericRule array
        ArrayList<GenericRule> parsedRules = new ArrayList<>();
        for (String rule : rules) {
            parsedRules.add(RulesFactory.getFactory().createRule(rule));
        }

        initEnabledButtonsToPlay(parser.getAcceptedKeys());
        ArrayList<Cell> clues = parser.getClues();
        ArrayList<RegionJson> regionJsons = parser.getRegionJsons();
        return new Puzzle(parser.getHeight(), parser.getWidth(), parsedRules, clues, regionJsons);
    }


    private void initEnabledButtonsToPlay(ArrayList<String> acceptedKeys) {
        InputUserView.createView(acceptedKeys);
    }

}
