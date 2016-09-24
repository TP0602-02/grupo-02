package ar.fiuba.tdd.template;

import java.util.ArrayList;

class Cell<T> {

    private ArrayList<CellContent<?>> contents;
    private int row;
    private int column;

    Cell(int row,int column) {
        this.row = row;
        this.column = column;
        contents = new ArrayList<>();
    }

    ArrayList<CellContent<?>> getContents() {
        return contents;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return  this.column;
    }

    public void setContent(CellContent content) {
        contents.add(content);
    }

    public boolean isEmpty() {
        return contents.size() == 0;
    }

}
