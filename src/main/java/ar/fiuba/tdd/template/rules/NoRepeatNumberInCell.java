package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.entity.Play;

public class NoRepeatNumberInCell extends GenericRule {
    @Override
    public boolean validate(Board board, Play play) {
        for (CellContent content : play.getSelectedCell().getSummableContents()) {
            if (content.getNumberValue() == play.getValueOfCell()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Valor existente en la celda";
    }
}
