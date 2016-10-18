package ar.fiuba.tdd.template.board.cell;

import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.entity.Coordinate;

import java.util.ArrayList;

public class ClueJson {

    private ArrayList<CellContent> contents; // all the contents that this clue contains
    private ArrayList<Coordinate> coordinates; // all the cells where this clue should be added
    private int clueID;

    public ClueJson() {
        this.coordinates = new ArrayList<>();
        this.contents = new ArrayList<>();
    }

    public void addCoordinate(Coordinate coordinate) {
        this.coordinates.add(coordinate);
    }

    public void addContents(CellContent content) {
        this.contents.add(content);
    }

    public ArrayList<CellContent> getContents() {
        return this.contents;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return this.coordinates;
    }

    public void setClueID(int id) {
        this.clueID = id;
    }

    public int getClueID() {
        return this.clueID;
    }

}
