package ar.fiuba.tdd.template.board.cell.model;

import java.util.ArrayList;

public abstract class Cell implements Summable, Editable {

    protected ArrayList<CellContent> contents;
    protected int row;
    protected int column;

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

    public abstract void setContent(CellContent newContentCell);
  /*  public void setContent(CellContent newContentCell) {
        if (!refreshEditableContent(newContentCell)) {
            contents.add(newContentCell);
        }
    }*/

   /* private boolean refreshEditableContent(CellContent newContentCell) {
        for (int index = 0; index < this.contents.size(); ++index) {
            if (this.contents.get(index).isEditable()) {
                //TODO caundo sea posible ingresar varios ValuesCell en una celda, hay que cambiarlo
                this.contents.remove(index);
                this.contents.add(index, newContentCell);
                return true;
            }
        }
        return false;
    }*/

    public boolean isEmpty() {
        return contents.size() == 0;
    }

    //Deben ser todos los contents posibles de editar para que la celda lo sea
    @Override
    public boolean isEditable() {
        boolean isEditable = true;
        for (CellContent cellContent : contents) {
            isEditable &= cellContent.isEditable();
        }
        return isEditable;
    }

    //Deben ser al menos uno de los contents posible de sumar para que la celda lo sea
    @Override
    public boolean isSummable() {
        boolean isSummable = false;
        for (CellContent cellContent : contents) {
            isSummable |= cellContent.isSummable();
        }
        return isSummable;
    }
}
