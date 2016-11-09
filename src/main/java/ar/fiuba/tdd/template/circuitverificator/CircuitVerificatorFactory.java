package ar.fiuba.tdd.template.circuitverificator;

import java.util.Properties;

/**
 * Created by matiaskamien on 20/10/16.
 */
public class CircuitVerificatorFactory {
    @SuppressWarnings("CPD-START")
    private static Properties factory;
    private static CircuitVerificatorFactory instance;

    public static final String CIRCUIT_VERIFICATOR_WITH_BORDERS = "Circuit verificator with borders";
    public static final String CIRCUIT_VERIFICATOR_WITH_DIAGONALS = "Circuit verificator with diagonals";
    public static final String CIRCUIT_VERIFICATOR_WITHOUT_BORDERS = "Circuit verificator without borders";


    public static CircuitVerificatorFactory getFactory() {
        if (instance == null) {
            instance = new CircuitVerificatorFactory();
            initFactory();
        }
        return instance;
    }

    private static void initFactory() {
        factory = new Properties();
        factory.put(CIRCUIT_VERIFICATOR_WITH_BORDERS, new CircuitVerificatorWithBorders());
        factory.put(CIRCUIT_VERIFICATOR_WITH_DIAGONALS, new CircuitVerificatorWithDiagonals());
        factory.put(CIRCUIT_VERIFICATOR_WITHOUT_BORDERS, new CircuitVerificatorWithoutBorders());
    }


    @SuppressWarnings("CPD-END")
    public CircuitVerificator createVerificator(String circuitVerificator) {
        return (CircuitVerificator) factory.getOrDefault(circuitVerificator, null);
    }


}
