package ar.fiuba.tdd.template.board.cell;

/**
 * Created by matiaskamien on 27/09/16.
 */
public interface BoardIterator {

    public Cell getLeftCell(Cell cell);

    public Cell getRightCell(Cell cell);

    public Cell getAboveCell(Cell cell);
    public Cell getBelowCell(Cell cell);
}
