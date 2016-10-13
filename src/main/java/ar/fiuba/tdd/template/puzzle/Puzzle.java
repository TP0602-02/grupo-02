package ar.fiuba.tdd.template.puzzle;

import ar.fiuba.tdd.template.Play;
import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.RegionFactory;
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
                  ArrayList<ArrayList<Cell>> regions, ArrayList<ArrayList<Cell>> exceptions) {
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
        setInitialRegions(regions, exceptions);
    }

    private void setInitialRegions(ArrayList<ArrayList<Cell>> regions, ArrayList<ArrayList<Cell>> exceptions) {
        for (int i = 0; i < regions.size(); i++) {
            ArrayList<Cell> fromToRegion = regions.get(i); // contains topLeft and bottomRight
            ArrayList<Cell> exceptionCells = exceptions.get(i); // contains cells that are not part of the region

            Region region = RegionFactory.getFactory().createRegion(this.board,
                    fromToRegion.get(0), fromToRegion.get(1), exceptionCells, "GENERIC REGION");
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
        if (this.validateMove(cell,Integer.parseInt(String.valueOf(cell.getContents().get(0).getValue())))) {
            // this.board.setValue(cell.getRow(), cell.getColumn(), new ValueContent<Integer>(valueToAdd));
            play.setValidPlay(true);
            return true;
        } else {
            play.setValidPlay(false);
            return false;
        }
    }

    private boolean validateMove(Cell cell, int valueToAdd) {
        for (GenericRule rule : this.rules) {
            if (!rule.validate(this.board, cell, valueToAdd)) {
                return false;
            }
        }
        return true;
    }

}

