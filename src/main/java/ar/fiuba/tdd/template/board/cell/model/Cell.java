package ar.fiuba.tdd.template.board.cell.model;

import java.util.ArrayList;

public class Cell implements Summable, Editable {

    private ArrayList<CellContent> contents;
    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        contents = new ArrayList<>();
    }

    public ArrayList<CellContent> getContents() {
        return contents;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setContent(CellContent content) {
        contents.add(content);
    }

    public boolean isEmpty() {
        return contents.size() == 0;
    }

    @Override
    public boolean isEditable() {
        boolean isEditable = true;
        for (CellContent cellContent : contents) {
            isEditable &= cellContent.isEditable();
        }
        return isEditable;
    }

    @Override
    public boolean isSummable() {
        boolean isSummable = false;
        for (CellContent cellContent : contents) {
            isSummable &= cellContent.isSummable();
        }
        return isSummable;
    }
}
