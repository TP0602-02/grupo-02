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

import java.util.ArrayList;

public class Puzzle {

    private Board board;
    private ArrayList<GenericRule> rules;
    private int boardHeight;
    private int boardWidth;
    private ArrayList<Cell> initialCells;

    public Puzzle(int boardHeight, int boardWidth, ArrayList<GenericRule> rules, ArrayList<Cell> initialCells,
                  ArrayList<RegionJson> regionJsons) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        // primero se crea el board
        this.board = new Board(boardHeight, boardWidth, CellFactory.CELL_SINGLE_VALUE);

        // después se definen las regiones
        setInitialRegions(regionJsons);

        setInitialCells(initialCells);
        this.initialCells = initialCells;

        // en vez de setear los valores asi ^ primero creamos las cells múltiples
        setMultipleCells(initialCells);

        // recién ahora habría que asignar los contenidos

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

    private void setMultipleCells(ArrayList<Cell> initialCells) {
        CellFactory cellFactory = new CellFactory();
        for (Cell cellToAdd : initialCells) {
            String cellType = getCellType(cellToAdd.getContents().size());
            Cell newCell = cellFactory.createCell(cellType, new Coordinate(cellToAdd.getRow(), cellToAdd.getColumn()));
            this.board.setCell(newCell);
        }
    }

    private void createInitialContents(ArrayList<ClueJson> cluesJson) {
        for (ClueJson clue : cluesJson) { // for each clue
            for (Coordinate coordinate : clue.getCoordinates()) {
                for (CellContent content : clue.getContents()) {
                    this.board.getCell(coordinate).setContent(content);
                }
            }
        }
    }

    private String getCellType(int size) {
        if (size > 1) {
            return CellFactory.CELL_MULTIPLE_VALUE;
        }
        return CellFactory.CELL_SINGLE_VALUE;
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

