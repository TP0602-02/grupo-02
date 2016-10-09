package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.board.cell.model.Cell;

/**
 * Created by martin on 08/10/16.
 */
public class Play {

    private Cell selectedCell;
    
    public Play(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }

    public Cell getSelectedCell() {
        return this.selectedCell;
    }

}
