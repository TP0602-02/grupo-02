package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.RegionCreator;
import ar.fiuba.tdd.template.board.cell.RegionJson;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellFactory;
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
        //TODO como ultimo parametro hay que pasarle lo que se levante del parser del archivo

        this.board = new Board(boardHeight, boardWidth, CellFactory.CELL_SINGLE_VALUE);
        setInitialCells(initialCells);
        this.initialCells = initialCells;
        this.rules = new ArrayList<GenericRule>();
        for (GenericRule rule : rules) {
            this.rules.add(rule);
        }
        setInitialRegions(regionJsons);
    }

    private void setInitialRegions(ArrayList<RegionJson> regionJsons) {
        RegionCreator regionCreator = new RegionCreator(this.board);
        for (RegionJson regionJson : regionJsons) {

            Region region = regionCreator.createRegion(regionJson.getLeftTop(),
                    regionJson.getRightBottom(), regionJson.getExceptions());
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

    public Cell getCell(int row, int column) {
        return this.board.getCell(row, column);
    }

    private void setInitialCells(ArrayList<Cell> initialCells) {
        for (Cell cellToAdd : initialCells) {
            this.board.setValues(cellToAdd.getRow(), cellToAdd.getColumn(), cellToAdd.getContents());
        }
    }

    public boolean checkMovement(Play play) {
        Cell cell = play.getSelectedCell();
        if (this.validateMove(cell, play.getSelectedCellValue())) {
            // this.board.setValue(cell.getRow(), cell.getColumn(), new ValueContent<Integer>(valueToAdd));
            play.setValidPlay(true);
            return true;
        } else {
            play.setValidPlay(false);
            return false;
        }
    }

    private boolean validateMove(Cell cell, int valueToAdd) {
        //TODO DESCOMENTAR ESTO CUANADO SE CREEN BIEN LOS NOMBRES DE LAS RULES CON LA FACTORY!!!
       /* for (GenericRule rule : this.rules) {
            if (!rule.validate(this.board, cell, valueToAdd)) {
                return false;
            }
        }*/
        return true;
    }

}

