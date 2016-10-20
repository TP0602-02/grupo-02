package ar.fiuba.tdd.template.rules;

import java.util.Properties;

public class RulesFactory {

    private static Properties factory;
    private static RulesFactory instance;
    public static final String SUM_RULE = "Sum rule";
    public static final String MULTIPLICATION_RULE = "Multiplication rule";
    public static final String NO_REPEAT_VALUE_RULE = "No repeat value rule";
    public static final String CONECTIONS_IN_CELL_RULE = "Conections in cell rule";
    public static final String CONECTIONS_IN_REGION_RULE = "Conections in region rule";
    public static final String CLOSE_CIRCUIT_RULE = "Close circuit Rule";
    public static final String CONECTIONS_INSIDE_BOARD_RULE = "Conections inside board rule";
    public static final String NUMBER_CONECTIONS_IN_REGION_RULE = "Number of conections in region rule";
    public static final String CLOSE_DIAGONAL_CIRCUIT_RULE = "Close circuit diagonal rule";
    public static final String NUMBER_DIAGONALS_RULE = "Number of diagonals in region";


    private static void initFactory() {
        factory = new Properties();
        putRules();
    }

    public static RulesFactory getFactory() {
        if (instance == null) {
            instance = new RulesFactory();
            initFactory();
        }
        return instance;
    }

    public GenericRule createRule(String ruleName) {
        return (GenericRule) factory.get(ruleName);
    }

    private static void putRules() {
        factory.put(SUM_RULE, new SumRule());
        factory.put(MULTIPLICATION_RULE, new MultiplicationRule());
        factory.put(NO_REPEAT_VALUE_RULE, new NoRepeatValueRule());
        factory.put(CONECTIONS_IN_CELL_RULE, new CellHasValidConectionsRule());
        factory.put(CONECTIONS_INSIDE_BOARD_RULE, new ConectionInsideBoardRule());
        factory.put(CONECTIONS_IN_REGION_RULE, new RegionHasValidConectionsRule());
        factory.put(CLOSE_CIRCUIT_RULE, new CloseCircuitConnectionRule());
        factory.put(NUMBER_CONECTIONS_IN_REGION_RULE, new NumberOfConectionsInRegionRule());
        factory.put(CLOSE_DIAGONAL_CIRCUIT_RULE, new CloseCircuitDiagonalRule());
        factory.put(NUMBER_DIAGONALS_RULE, new NumberOfDiagonalsRule());
    }
}

