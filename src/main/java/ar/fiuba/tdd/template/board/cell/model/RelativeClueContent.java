package ar.fiuba.tdd.template.board.cell.model;

import java.awt.*;

/**
 * Created by matiaskamien on 17/10/16.
 */
public class RelativeClueContent extends CellContentWithValue {

    private ClueContent clue;

    public RelativeClueContent(ClueContent clue, int value) {
        super(value);
        this.clue = clue;
    }

    @Override
    public Color getColorRepresentation() {
        return Color.YELLOW;
    }

    public ClueContent getClue() {
        return clue;
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public boolean isSummable() {
        return false;
    }

    @Override
    public boolean isShowableInBoard() {
        return false;
    }

    @Override
    public boolean isDeleteable() {
        return false;
    }
}
