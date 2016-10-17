package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.RelativeClueContent;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by matiaskamien on 17/10/16.
 */
public class NumberOfDiagonalsRule extends NumberRule {

    private static final int upperLeftCorner = 1;
    private static final int upperRightCorner = 2;
    private static final int lowerLeftCorner = 3;
    private static final int lowerRightCorner = 4;

    @Override
    public boolean validateRegion(Region region, Cell cell, int numberToAdd) {
        return (region.getDiagonalsPartial() < region.getTotal());
    }

    @Override
    protected void initializeTotals(Region region) {

    }

    @Override
    public ArrayList<Region> getRegions(Board board, Cell cell, int numberToAdd) {
        ArrayList<Region> regions = new ArrayList<Region>();
        ArrayList<ClueContent> clues = this.getClues(cell, numberToAdd);
        for (ClueContent clue: clues) {
            regions.add(this.getRegion(board, clue));
        }
        return regions;
    }

    private Region getRegion(Board board, ClueContent clue) {
        ArrayList<Region> regions = board.getRegions();
        for (Region region: regions) {
            if (region.getClue() == clue) {
                return region;
            }
        }
        return null;
    }

    private ArrayList<ClueContent> getClues(Cell cell, int numberToAdd) {
        ArrayList<ClueContent> clues = new ArrayList<ClueContent>();
        ArrayList<Integer> corners = this.getCorners(numberToAdd);
        ArrayList<RelativeClueContent> relativeClueContents = cell.getPositionContents();
        for (Integer corner: corners) {
            for (RelativeClueContent relativeClueContent: relativeClueContents) {
                if (relativeClueContent.getCorner() == corner) {
                    clues.add(this.getClueContent(cell, corner));
                    break;
                }
            }
        }
        return clues;
    }

    /*private boolean hasClueInCorner(Cell cell, Integer corner) {
        ArrayList<RelativeClueContent> relativeClueContents = cell.getPositionContents();
        for (RelativeClueContent relativeClueContent: relativeClueContents) {
            if (relativeClueContent.getCorner() == corner) {
                return true;
            }
        }
        return false;
    }*/

    private ClueContent getClueContent(Cell cell, Integer corner) {
        return null;
    }

    private ArrayList<Integer> getCorners(int numberToAdd) {
        ArrayList<Integer> corners = new ArrayList<Integer>();
        switch (numberToAdd) {
            case 1: corners.add(this.upperLeftCorner);
                    corners.add(this.lowerRightCorner);
                    return corners;
            case 2: corners.add(this.upperRightCorner);
                    corners.add(this.lowerLeftCorner);
                    return corners;
            default: return corners;
        }
    }
}
