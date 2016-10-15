package ar.fiuba.tdd.template.entity;

import java.util.Properties;

/**
 * Created by Nicolas on 9/10/2016.
 */
public class SpecialCharactersParser {

    public static final String BARRA = "/";
    public static final String BARRA_INVERTIDA = "\\";
    public static final String GUION_MEDIO = "-";
    public static final String GUION_BAJO = "_";

    //TODO DEFINIR VALORES!!!
    public static final int BARRA_VALUE = 0;
    public static final int BARRA_INVERTIDA_VALUE = 0;
    public static final int GUION_MEDIO_VALUE = 1;
    public static final int GUION_BAJO_VALUE = 0;


    private static Properties table;
    private static SpecialCharactersParser instance;

    private static void initTable() {
        table = new Properties();
        table.put(BARRA, BARRA_VALUE);
        table.put(BARRA_INVERTIDA, BARRA_INVERTIDA_VALUE);
        table.put(GUION_MEDIO, GUION_MEDIO_VALUE);
        table.put(GUION_BAJO, GUION_BAJO_VALUE);
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
        } catch (NumberFormatException exception) {
            return (int) table.getOrDefault(text, 0);
        }
    }
}
