package ar.fiuba.tdd.template.puzzle.controller;


import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.region.RegionJson;
import ar.fiuba.tdd.template.drawers.DrawerFactory;
import ar.fiuba.tdd.template.entity.Parser;
import ar.fiuba.tdd.template.puzzle.model.Puzzle;
import ar.fiuba.tdd.template.puzzle.view.InputUserView;
import ar.fiuba.tdd.template.puzzle.view.PuzzleView;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.RulesFactory;
import ar.fiuba.tdd.template.userinterface.view.StartView;
import ar.fiuba.tdd.template.winverificators.WinVerificator;
import ar.fiuba.tdd.template.winverificators.WinVerificatorFactory;

import java.util.ArrayList;

public class PuzzleGenerator {
    private Parser parser;
    private PuzzleController puzzleController;
    private StartView startView;

    public void runGeneration() {
        startView = new StartView(new StartView.StartGameListener() {
            @Override
            public void loadNewGame(String gameName, String gameFile) {
                initParse(gameFile, null);
                createGame(gameName, true);
            }

            @Override
            public void loadPlaysForGame(String playFile, String gameFile, String gameName) {
                initParse(gameFile, playFile);
                createGame(gameName, false);
                puzzleController.execPlays(parser.getPlays(), gameName);
            }
        });
        startView.start();
    }

    /**
     * Firstable must call initParser method.
     *
     * @return .
     */
    private void createGame(String gameName, boolean showPuzzleToPlay) {
        Puzzle puzzle = startGeneration();
        ArrayList<Cell> graphicsInitialCells = parser.getGraphicsInitialClues();
        graphicsInitialCells.addAll(puzzle.getInitialCells());

        PuzzleView puzzleView = new PuzzleView(puzzle.getBoardHeight(), puzzle.getBoardWidth(),
                gameName, graphicsInitialCells, parser.getInstructionGame(), puzzle.getRegions());
        puzzleView.setVisible(showPuzzleToPlay);
        initBackListener(puzzleView);

        puzzleController = new PuzzleController(createWinVerificators(), this.parser.getAgreggator());
        puzzleController.attachElements(puzzleView, puzzle);
        puzzleController.aggregateCellControllers();
    }

    private ArrayList<WinVerificator> createWinVerificators() {
        // Converts win verificator array of strings into WinVerificator array
        ArrayList<String> winVerificators = parser.getWinVerificators();
        ArrayList<WinVerificator> parsedWinVerificators = new ArrayList<>();
        for (String verificator : winVerificators) {
            WinVerificator winVerificator = WinVerificatorFactory.getFactory().createVerificator(verificator);
            winVerificator.setVerificator(this.parser.getCircuitVerificator());
            parsedWinVerificators.add(winVerificator);
        }
        return parsedWinVerificators;
    }

    private void initBackListener(PuzzleView puzzleView) {
        puzzleView.setBackListener(new PuzzleView.BackPressed() {
            @Override
            public void onBackClick() {
                puzzleView.setVisible(false);
                startView.setVisible(true);
            }
        });
    }

    private void initParse(String fileName, String playsFileName) {
        parser = new Parser();
        parser.decodeJson(fileName, playsFileName);
    }

    private Puzzle startGeneration() {
        DrawerFactory.createDrawer(parser.getDrawerName());
        initEnabledButtonsToPlay(parser.getAcceptedKeys());
        ArrayList<Cell> clues = parser.getClues();
        ArrayList<RegionJson> regionJsons = parser.getRegionJsons();
        return new Puzzle(parser.getHeight(), parser.getWidth(), createRules(), clues, regionJsons, parser.getCellType());
    }

    private ArrayList<GenericRule> createRules() {
        // Converts rules array of strings into GenericRule array
        ArrayList<String> rules = parser.getRules();
        ArrayList<GenericRule> parsedRules = new ArrayList<>();
        for (String rule : rules) {
            parsedRules.add(RulesFactory.getFactory().createRule(rule));
        }
        return parsedRules;
    }

    private void initEnabledButtonsToPlay(ArrayList<String> acceptedKeys) {
        InputUserView.createView(acceptedKeys);
    }

}
