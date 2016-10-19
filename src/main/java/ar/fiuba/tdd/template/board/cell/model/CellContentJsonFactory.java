package ar.fiuba.tdd.template.board.cell.model;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Nicolas on 19/10/2016.
 */
public class CellContentJsonFactory {
    private Properties contents;
    private static final int NO_VALUE = -1;

    public CellContentJsonFactory() {
        this.contents = new Properties();
    }

    public CellContent getCellContent(int idCellContent) {
        return ((CellContent) contents.getOrDefault(idCellContent, null));
    }

    public void createContent(CellContentJson cellContentJson) {
        CellContent cellContent = null;
        if (cellContentJson.getValue() == NO_VALUE) {
            cellContent = new BlackContent();
        } else {
            cellContent = new ClueContent(cellContentJson.getValue());
        }
        this.contents.put(cellContentJson.getId(), cellContent);
    }
}
