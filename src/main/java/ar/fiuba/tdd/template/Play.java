package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by martin on 08/10/16.
 */
public class Play {
    // Criteria of acceptance : we only add single cell values
    private Cell selectedCell;
    private int selectedValue;
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

    public void setSelectedValue(int value) {
        this.selectedValue = value;
    }


    public int getSelectedCellValue() {
        return selectedValue;
    }

}
