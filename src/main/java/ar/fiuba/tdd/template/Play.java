package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.GenericValue;

/**
 * Created by martin on 08/10/16.
 */
public class Play {
    // Criteria of acceptance : we only add single cell values
    private Cell selectedCell;
    private GenericValue selectedValue;
    private boolean validPlay;
    
    public Play(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }

    public void setValidPlay(boolean validPlay) {
        this.validPlay = validPlay;
    }

    public boolean getValidPlay() {
        return this.validPlay;
    }

    public Cell getSelectedCell() {
        return this.selectedCell;
    }

    public void setSelectedValue(String value) {
        this.selectedValue = new GenericValue(value.charAt(0));
    }

    public void setSelectedValue(int value) {
        this.selectedValue = new GenericValue(String.valueOf(value).charAt(0));
    }

    public GenericValue getSelectedCellValue() {
        return selectedValue;
    }

}
