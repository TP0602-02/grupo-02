package ar.fiuba.tdd.template.entity;

import ar.fiuba.tdd.template.board.cell.model.Cell;

public class Play {
    // Criteria of acceptance : we only add single cell values
    private Cell selectedCell;
    private String selectedValue;
    private boolean validPlay;
    private String errorMessge;

    public Play(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }

    public Play(Cell selectedCell, String selectedValue) {
        this.selectedCell = selectedCell;
        this.selectedValue = selectedValue;
    }

    public int getValueOfCell() {
        return SpecialCharactersParser.getInstance().getValueOf(this.getSelectedCellValue());
    }

    public boolean getValidPlay() {
        return this.validPlay;
    }

    public void setValidPlay(boolean validPlay) {
        this.validPlay = validPlay;
    }

    public Cell getSelectedCell() {
        return this.selectedCell;
    }

    public void setSelectedValue(String value) {
        this.selectedValue = value;
    }

    public String getSelectedCellValue() {
        return selectedValue;
    }

    public String getErrorMessge() {
        return errorMessge;
    }

    public void setErrorMessge(String errorMessge) {
        this.errorMessge = errorMessge;
    }
}
