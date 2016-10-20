package ar.fiuba.tdd.template.entity;

import java.util.Properties;

/**
 * Created by Nicolas on 9/10/2016.
 */
public class SpecialCharactersParser {

    private static Properties table;
    private static SpecialCharactersParser instance;

    private static void initTable() {
        table = new Properties();
        table.put(Constants.DOWN, Constants.DOWN_VALUE);
        table.put(Constants.DOWN_DIAGONAL, Constants.DOWN_DIAGONAL_VALUE);
        table.put(Constants.UP, Constants.UP_VALUE);
        table.put(Constants.UP_DIAGONAL, Constants.UP_DIAGONAL_VALUE);
        table.put(Constants.RIGHT, Constants.RIGHT_VALUE);
        table.put(Constants.LEFT, Constants.LEFT_VALUE);
    }

    public static SpecialCharactersParser getInstance() {
        if (instance == null) {
            instance = new SpecialCharactersParser();
            initTable();
        }
        return instance;
    }

    public int getValueOf(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException | ClassCastException exception) {
            return (int) table.getOrDefault(text, 0);
        }
    }
}
