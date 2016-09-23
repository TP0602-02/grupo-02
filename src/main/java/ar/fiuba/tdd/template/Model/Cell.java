package ar.fiuba.tdd.template;

import java.util.ArrayList;

class Cell<T> {

    private ArrayList<CellContent<?>> contents;
    private boolean isChangeable = true;

    Cell() {
        contents = new ArrayList<>();
        contents.add(new BlankCell());
    }

    ArrayList<CellContent<?>> getContents() {
        return contents;
    }

    void setContent(CellContent content) throws AssertionError {
        if (!isChangeable) {
            throw new AssertionError("This cell has a BlackCell on it");
        }

        CellContent<?> blank = null;
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i) instanceof BlankCell) {
                blank = contents.get(i);
            }
        }

        if (blank != null) {
            contents.remove(blank);
        }

        content.setChangeable(this);
        contents.add(content);
    }

    void setChangeable(boolean value) {
        isChangeable = value;
    }
}
