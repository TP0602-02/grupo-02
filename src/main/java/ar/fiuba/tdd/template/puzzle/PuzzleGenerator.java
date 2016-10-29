package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Parser;
import ar.fiuba.tdd.template.board.InputUserView;
import ar.fiuba.tdd.template.board.cell.RegionJson;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.puzzle.aggregators.AbstractAgreggator;
import ar.fiuba.tdd.template.puzzle.aggregators.Aggregator;
import ar.fiuba.tdd.template.puzzle.aggregators.AggregatorWithConnections;
import ar.fiuba.tdd.template.puzzle.aggregators.AggregatorWithDiagonals;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.RulesFactory;
import ar.fiuba.tdd.template.userinterface.view.PuzzleView;
import ar.fiuba.tdd.template.userinterface.view.StartView;
import ar.fiuba.tdd.template.winverificators.WinVerificator;
import ar.fiuba.tdd.template.winverificators.WinVerificatorFactory;

import java.util.ArrayList;

public class PuzzleGenerator {
    private Parser parser;
    private PuzzleController puzzleController;

    public void runGeneration() {
        StartView startView = new StartView(new StartView.StartGameListener() {
            @Override
            public void loadNewGame(String gameName, String gameFile) {
                initParse(gameFile, null);
                createGame(gameName, true);
            }

            @Override
            public void loadPlaysForGame(String playFile, String gameFile) {
                initParse(gameFile, playFile);
                createGame("", false);
                puzzleController.execPlays(parser.getPlays());
            }
        });
        startView.start();
    }

    /**
     * Firstable must call initParser method.
     *
     * @return .
     */
    public void createGame(String gameName, boolean showPuzzleToPlay) {
        Puzzle puzzle = startGeneration();
        PuzzleView puzzleView = new PuzzleView(puzzle.getBoardHeight(), puzzle.getBoardWidth(),
                puzzle.getInitialCells(), gameName);
        puzzleView.setVisible(showPuzzleToPlay);

        ArrayList<String> winVerificators = parser.getWinVerificators();
        // Converts win verificator array of strings into WinVerificator array
        ArrayList<WinVerificator> parsedWinVerificators = new ArrayList<>();
        for (String verificator : winVerificators) {
            WinVerificator winVerificator = WinVerificatorFactory.getFactory().createVerificator(verificator);
            winVerificator.setVerificator(this.parser.getCircuitVerificator());
            parsedWinVerificators.add(winVerificator);
        }
        puzzleController = new PuzzleController(parsedWinVerificators,this.parser.getAgreggator());
        puzzleController.attachElements(puzzleView, puzzle);
        puzzleController.aggregateCellControllers();
    }

    public PuzzleGenerator() {
        parser = new Parser();
    }

    public Parser getParser() {
        return this.parser;
    }

    public PuzzleController getPuzzleController() {
        return this.puzzleController;
    }

    private void initParse(String fileName, String playsFileName) {
        parser.decodeJson(fileName, playsFileName);

    }

    private Puzzle startGeneration() {
        ArrayList<String> rules = parser.getRules();

        // Converts rules array of strings into GenericRule array
        ArrayList<GenericRule> parsedRules = new ArrayList<>();
        for (String rule : rules) {
            parsedRules.add(RulesFactory.getFactory().createRule(rule));
        }

        initEnabledButtonsToPlay(parser.getAcceptedKeys());
        ArrayList<Cell> clues = parser.getClues();
        ArrayList<RegionJson> regionJsons = parser.getRegionJsons();
        return new Puzzle(parser.getHeight(), parser.getWidth(), parsedRules, clues, regionJsons, parser.getCellType());
    }


    private void initEnabledButtonsToPlay(ArrayList<String> acceptedKeys) {
        InputUserView.createView(acceptedKeys);
    }

}
