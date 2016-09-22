package ar.fiuba.tdd.template;

import java.util.ArrayList;

class Cell<T> {

    private ArrayList<CellContent<?>> contents;

    Cell() {
        contents = new ArrayList<>();
        contents.add(new BlankCell()); // a new BlankCell should be created here
    }

    ArrayList<CellContent<?>> getContents() {
        return contents;
    }

    void setContent(CellContent content) {
        contents.remove(0);
        contents.add(content);
    }
}
