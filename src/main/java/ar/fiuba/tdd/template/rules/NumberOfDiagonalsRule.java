package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.board.cell.model.RelativeClueContent;
import ar.fiuba.tdd.template.board.region.Region;
import ar.fiuba.tdd.template.entity.Play;

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
    public boolean validateRegion(Region region, Play play) {
        ArrayList<Integer> corners = this.getCorners(play.getValueOfCell());
        ArrayList<RelativeClueContent> positions = play.getSelectedCell().getPositionContents();
        RelativeClueContent positionClue = matchClueWithRegion(positions, region);
        if (!matchCorner(corners, positionClue.getNumberValue())) {
            return true;
        }
        return (region.getDiagonalsPartial() < region.getClue().getNumberValue());
    }

    private boolean matchCorner(ArrayList<Integer> corners, int corner) {
        for (Integer candidateCorner : corners) {
            if (candidateCorner == corner) {
                return true;
            }
        }
        return false;
    }

    private RelativeClueContent matchClueWithRegion(ArrayList<RelativeClueContent> positions, Region region) {
        for (RelativeClueContent position : positions) {
            if (position.getClue() == region.getClue()) {
                return position;
            }
        }
        return null;
    }

    @Override
    protected void initializeTotals(Region region) {

    }

    private ClueContent getClueContent(Cell cell, Integer corner) {
        return null;
    }

    private ArrayList<Integer> getCorners(int numberToAdd) {
        ArrayList<Integer> corners = new ArrayList<Integer>();
        switch (numberToAdd) {
            case 1:
                corners.add(this.upperLeftCorner);
                corners.add(this.lowerRightCorner);
                return corners;
            case 2:
                corners.add(this.upperRightCorner);
                corners.add(this.lowerLeftCorner);
                return corners;
            default:
                return corners;
        }
    }

    @Override
    public String toString() {
        return "La cantidad de diagonales no es la que indica la pista";
    }
}
