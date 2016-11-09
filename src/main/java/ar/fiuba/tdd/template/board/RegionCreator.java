package ar.fiuba.tdd.template.board;

import ar.fiuba.tdd.template.board.cell.RegionJson;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.entity.Coordinate;

import java.util.ArrayList;

public class RegionCreator {
   /* private Board board;

    public RegionCreator(Board board) {
        this.board = board;
    }

    public Region createRegion(RegionJson regionJson) {
        return getRegion(regionJson);
    }

    private Region getRegion(RegionJson regionJson) {
        ArrayList<Cell> regionCells = new ArrayList<>();
        for (int coordX = regionJson.getLeftTop().getRow(); coordX <= regionJson.getRightBottom().getRow(); coordX++) {
            for (int coordY = regionJson.getLeftTop().getColumn(); coordY <= regionJson.getRightBottom().getColumn(); coordY++) {
                Coordinate coordinate = new Coordinate(coordX, coordY);
                if (!inExceptions(coordinate, regionJson.getExceptions())) {
                    regionCells.add(board.getCell(coordinate)); // same cell as board
                }
            }
        }
        Region newRegion = new Region(regionCells);
        newRegion.setGraficable(regionJson.isGraficable());
        return newRegion;
    }

    private boolean inExceptions(Coordinate coordinate, ArrayList<Cell> exceptions) {
        for (Cell exception : exceptions) {
            if (exception.getRow() == coordinate.getRow() && exception.getColumn() == coordinate.getColumn()) {
                return true;
            }
        }
        return false;
    }*/


}

