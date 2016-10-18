package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.RegionCreator;
import ar.fiuba.tdd.template.board.cell.ClueJson;
import ar.fiuba.tdd.template.board.cell.RegionJson;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.SpecialCharactersParser;
import ar.fiuba.tdd.template.rules.GenericRule;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Puzzle {

    private Board board;
    private ArrayList<GenericRule> rules;
    private int boardHeight;
    private int boardWidth;
    private ArrayList<Cell> initialCells;

    public Puzzle(int boardHeight, int boardWidth, String cellType, ArrayList<GenericRule> rules, ArrayList<Cell> initialCells,
                  ArrayList<RegionJson> regionJsons, ArrayList<ClueJson> cluesJson) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        // primero se crea el board
        this.board = new Board(boardHeight, boardWidth, cellType);
        // después se definen las regiones
        setInitialRegions(regionJsons);
        //setInitialCells(initialCells);
        this.initialCells = initialCells;

        // recién ahora habría que asignar los contenidos
        createInitialContents(cluesJson);

        this.rules = new ArrayList<>();
        for (GenericRule rule : rules) {
            this.rules.add(rule);
        }
    }

    private void setInitialRegions(ArrayList<RegionJson> regionJsons) {
        RegionCreator regionCreator = new RegionCreator(this.board);
        for (RegionJson regionJson : regionJsons) {
            Region region = regionCreator.createRegion(regionJson.getLeftTop(),
                    regionJson.getRightBottom(), regionJson.getExceptions());
            region.setTotal(regionJson.getTotal());
            System.out.print(regionJson.getTotal() + "\n");
            board.addRegion(region);
        }
    }

    public ArrayList<Cell> getInitialCells() {
        return this.initialCells;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public Cell getCell(Coordinate coordinate) {
        return this.board.getCell(coordinate);
    }

    private void setInitialCells(ArrayList<Cell> initialCells) {
        for (Cell cellToAdd : initialCells) {
            this.board.setValues(new Coordinate(cellToAdd.getRow(), cellToAdd.getColumn()), cellToAdd.getContents());
        }
    }

    private void createInitialContents(ArrayList<ClueJson> cluesJson) {
        for (ClueJson clue : cluesJson) { // for each clue
            System.out.print(clue.getClueID() + "\n");
            for (Coordinate coordinate : clue.getCoordinates()) {
                for (CellContent content : clue.getContents()) {
                    this.board.getCell(coordinate).setContent(content);
                }
            }
        }
    }

    public boolean checkMovement(Play play) {
        boolean validPlay = this.validateMove( play.getSelectedCell(),
                SpecialCharactersParser.getInstance().getValueOf(play.getSelectedCellValue()));
        play.setValidPlay(validPlay);
        return validPlay;
    }

    private boolean validateMove(Cell cell, int valueToAdd) {
        //TODO DESCOMENTAR ESTO CUANADO SE CREEN BIEN LOS NOMBRES DE LAS RULES CON LA FACTORY!!!
        for (GenericRule rule : this.rules) {
            if (!rule.validate(this.board, cell, valueToAdd)) {
                return false;
            }
        }
        return true;
    }

    public Board getBoard() {
        return board;
    }
}

