package ar.fiuba.tdd.template;


import java.util.ArrayList;


class BlackCell<T> extends CellContent<T> {

    private ArrayList<ClueCell<?>> clues;

    BlackCell() {
        clues = new ArrayList<>();
        ClueCell<String> clue = new ClueCell<>("Empty Value");
        clues.add(clue);
    }

    BlackCell(ArrayList<T> values) {

        clues = new ArrayList<>();

        // Add clue values to Black cell

        for (int i = 0; i < values.size(); i++) {
            clues.add(new ClueCell<>(values.get(i)));
        }

    }

    ArrayList<T> getClues() {

        ArrayList<T> values = new ArrayList<>();

        for ( ClueCell<?> clue : clues) {
            T value = (T)clue.getValue();
            values.add(value);
        }

        return values;

    }

    T getValue() throws AssertionError {
        throw new AssertionError("Cannot get value");
    }

    void setValue(T value) throws AssertionError {
        throw new AssertionError("Cannot set value");
    }

    @Override
    void setChangeable(Cell cell) {
        cell.setChangeable(false);
    }
}
