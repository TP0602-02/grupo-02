package ar.fiuba.tdd.template.board.cell.model;

import java.awt.*;

/**
 * Created by matiaskamien on 17/10/16.
 */
public class RelativeClueContent extends CellContent {

    private ClueContent clue;
    private int corner;

    public RelativeClueContent(ClueContent clue, int value) {
        this.clue = clue;
        this.corner = value;
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public boolean isSummable() {
        return false;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public int getNumberValue() {
        return 0;
    }

    @Override
    public void setValue(String value) {

    }

    @Override
    public Color getColorRepresentation() {
        return Color.WHITE;
    }

    public int getCorner() {
        return this.corner;
    }

    public ClueContent getClue() {
        return clue;
    }
}
