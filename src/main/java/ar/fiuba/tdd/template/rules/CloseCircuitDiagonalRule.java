package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.board.Board;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorWithDiagonals;
import ar.fiuba.tdd.template.entity.Play;

/**
 * Created by matiaskamien on 19/10/16.
 */
public class CloseCircuitDiagonalRule extends GenericCloseCircuitRule {

    private static final int SLASH = 1;
    private static final int BACK_SLASH = 2;

    public CloseCircuitDiagonalRule() {
        super();
        this.verificator = new CircuitVerificatorWithDiagonals();
    }

    @Override
    public boolean validateTotalRegionRule(Board board) {
        return true;
    }

    @Override
    public boolean validate(Board board, Play play) {
        Cell cell = play.getSelectedCell();
        this.generateMovement(cell, play.getValueOfCell());
        this.verificator.setCell(cell);
        if (this.verificator.isCircuitClosed(board)) {
            this.removeMovement(cell);
            return false;
        }
        this.removeMovement(cell);
        return true;
    }


    //Removes last two contents.
    private void removeMovement(Cell cell) {
        int lastContentPosition = cell.getContents().size() - 1;
        cell.removeContent(lastContentPosition);
        cell.removeContent(lastContentPosition - 1);
    }


    public void generateMovement(Cell cell, int numberToAdd) {
        switch (numberToAdd) {
            case SLASH:
                cell.addContent(new ValueContent(1));
                cell.addContent(new ValueContent(4));
                break;
            case BACK_SLASH:
                cell.addContent(new ValueContent(2));
                cell.addContent(new ValueContent(3));
                break;
            default:
                break;
        }
    }

    @Override
    protected boolean checkOtherMethods(Board board) {
        return false;
    }

    @Override
    protected void injectCellToStartVerification(Cell selectedCell) {

    }
}
