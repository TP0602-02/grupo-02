package ar.fiuba.tdd.template.board.cell.model;

/**
 * Created by Nicolas on 7/10/2016.
 */
public class CellMultipleValue extends Cell {
    public CellMultipleValue(int row, int column) {
        super(row, column);
    }

    @Override
    public void setContent(CellContent newContentCell) {
        contents.add(newContentCell);
    }
}
