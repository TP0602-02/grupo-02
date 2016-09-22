package ar.fiuba.tdd.template;

import java.util.ArrayList;

class Cell<T> {

    private ArrayList<CellContent<?>> contents;

    Cell() {
        contents = new ArrayList<>();
        contents.add(new BlankCell()); // a new BlankCell should be created here
    }

    T getValue() {
        return (T)contents.get(0).getValue(); // change this once the specific CellContents are created
    }

    void setValue(T value) {
        contents.remove(0);
        contents.add(new ValueCell<>(value)); // change this once the specific CellContents are created
    }
}
