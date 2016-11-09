package ar.fiuba.tdd.template.puzzle.model;


import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.board.region.RegionJson;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.entity.Play;
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
                  ArrayList<RegionJson> regionJsons, String cellType) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.board = new Board(boardHeight, boardWidth, cellType);
        setInitialCells(initialCells);
        this.initialCells = initialCells;
        this.rules = new ArrayList<>();
        this.rules.addAll(rules);
        setInitialRegions(regionJsons);
        printRegions();
    }

    private void printRegions() {
        this.board.printRegions();
    }

    private void setInitialRegions(ArrayList<RegionJson> regionJsons) {
        this.board.setInitialRegions(regionJsons);
    }

    public ArrayList<Region> getRegions() {
        return board.getRegions();
    }

    public ArrayList<Cell> getInitialCells() {
        return this.initialCells;
    }

    private void setInitialCells(ArrayList<Cell> initialCells) {
        for (Cell cellToAdd : initialCells) {
            this.board.setCell(cellToAdd);
        }
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

    public boolean checkMovement(Play play) {
        boolean validPlay = this.validateMove(play.getSelectedCell(),
                SpecialCharactersParser.getInstance().getValueOf(play.getSelectedCellValue()));
        play.setValidPlay(validPlay);
        return validPlay;
    }

    private boolean validateMove(Cell cell, int valueToAdd) {
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

