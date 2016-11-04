package ar.fiuba.tdd.template.drawers;

import ar.fiuba.tdd.template.board.cell.view.CellView;

/**
 * Created by Nicolas on 3/11/2016.
 */
public class ComplexDrawer extends AbstractDrawer {

    @Override
    protected void decorateCell(CellView cellViewToDraw) {

    }

    @Override
    protected String getImagePackage() {
        return "src/images/border-images/";
    }
}
