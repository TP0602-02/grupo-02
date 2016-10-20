package ar.fiuba.tdd.template.board.cell.model;

import ar.fiuba.tdd.template.board.cell.controller.CellController;
import ar.fiuba.tdd.template.entity.Coordinate;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Cell implements Summable, Editable {

    protected ArrayList<CellContent> contents;
    protected Coordinate coordinate;

    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
        contents = new ArrayList<>();
    }

    public ArrayList<CellContent> getContents() {
        return contents;
    }

    public ArrayList<CellContent> getSummableContents() {
        ArrayList<CellContent> contents = new ArrayList<CellContent>();
        for (CellContent cellContent : this.getContents()) {
            if (cellContent.isSummable()) {
                contents.add(cellContent);
            }
        }
        return contents;
    }

    public ArrayList<RelativeClueContent> getPositionContents() {
        ArrayList<RelativeClueContent> contents = new ArrayList<RelativeClueContent>();
        for (CellContent cellContent : this.getContents()) {
            if (!cellContent.isSummable()) {
                contents.add((RelativeClueContent) cellContent);
            }
        }
        return contents;
    }

    public int getRow() {
        return this.coordinate.getRow();
    }

    public int getColumn() {
        return this.coordinate.getColumn();
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

    public void removeContentWithValue(String value) {
        for (CellContent content : contents) {
            if (content.getValue().equals(value) && content.isDeleteable()) {
                contents.remove(content);
                return;
            }
        }
    }

    public ArrayList<String> getValuesToString() {
        ArrayList<String> values = new ArrayList<>();
        for (CellContent content : contents) {
            values.add(content.getValue());
        }
        return values;
    }

    public void removeContent(CellContent content) {
        if (this.contents.contains(content)) {
            this.contents.remove(content);
        }
    }

    public int getQuantityOfValues() {
        int total = 0;
        for (CellContent cellContent : contents) {
            if (cellContent.isSummable()) {
                total++;
            }
        }
        return total;
    }

    public boolean hasValue(int numberValue) {
        for (CellContent cellContent : this.contents) {
            if (cellContent.isSummable() && cellContent.getNumberValue() == numberValue) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getShowableValues() {
        ArrayList<String> values = new ArrayList<>();
        for (CellContent content : contents) {
            if (content.isShowableInBoard()) {
                values.add(content.getValue());
            }
        }
        return values;
    }

    public ArrayList<String> getDeleteableValues() {
        ArrayList<String> values = new ArrayList<>();
        for (CellContent content : contents) {
            if (content.isDeleteable()) {
                values.add(content.getValue());
            }
        }
        return values;
    }

    public boolean hasDeleteables() {
        for (CellContent cellContent: this.contents) {
            if (cellContent.isDeleteable()) {
                return true;
            }
        }
        return false;
    }
}
