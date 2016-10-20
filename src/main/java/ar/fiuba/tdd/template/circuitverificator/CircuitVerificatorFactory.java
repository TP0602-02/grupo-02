package ar.fiuba.tdd.template.circuitverificator;

import java.util.Properties;

/**
 * Created by matiaskamien on 20/10/16.
 */
public class CircuitVerificatorFactory {
    private static Properties factory;
    private static CircuitVerificatorFactory instance;

   /* public static final String CIRCUIT_VERIFICATOR_WITH_BORDERS = "Circuit verificator with borders";
    public static final String CIRCUIT_VERIFICATOR_WITH_DIAGONALS = "Circuit verificator with diagonals";
    public static final String CIRCUIT_VERIFICATOR_WITHOUT_BORDERS = "Circuit verificator without borders";
*/

    public static CircuitVerificatorFactory getFactory() {
        if (instance == null) {
            instance = new CircuitVerificatorFactory();
            initFactory();
        }
        return instance;
    }

    private static void initFactory() {
        factory = new Properties();
        factory.put("Circuit verificator with borders", new CircuitVerificatorWithBorders());
        factory.put("Circuit verificator with diagonals", new CircuitVerificatorWithDiagonals());
        factory.put("Circuit verificator without borders", new CircuitVerificatorWithoutBorders());
    }

    public CircuitVerificator createVerificator(String circuitVerificator) {
        return (CircuitVerificator) factory.getOrDefault(circuitVerificator,null);
    }

}
