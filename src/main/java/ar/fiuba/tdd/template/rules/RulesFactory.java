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
    public static final String CLOSE_CIRCUIT_BORDER_RULE = "Close circuit border rule";
    public static final String TOTAL_REGION_BORDER_RULE = "Total region border rule";
    public static final String TOTAL_REGION_CONNECTION_RULE = "Total region connection rule";
    public static final String CONECTIONS_INSIDE_BOARD_RULE = "Conections inside board rule";
    public static final String NUMBER_CONECTIONS_IN_REGION_RULE = "Number of conections in region rule";
    public static final String NUMBER_BORDERS_IN_REGION_RULE = "Number of borders in region rule";
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

    private RulesFactory(){

    }

    private static void addSecondRules() {
        factory.put(CONECTIONS_IN_REGION_RULE, new RegionHasValidConectionsRule());
        factory.put(NUMBER_CONECTIONS_IN_REGION_RULE, new NumberOfConectionsInRegionRule());
        factory.put(NUMBER_BORDERS_IN_REGION_RULE, new NumberOfBordersInRegionRule());
        factory.put(CLOSE_CIRCUIT_BORDER_RULE, new CloseCircuitBorderRule());
        factory.put(TOTAL_REGION_BORDER_RULE, new RegionTotalBorderRule());
        factory.put(TOTAL_REGION_CONNECTION_RULE, new RegionTotalConnectionRule());
        factory.put(CLOSE_CIRCUIT_RULE, new CloseCircuitConnectionRule());
        factory.put(SUM_RULE, new SumRule());
        factory.put(MULTIPLICATION_RULE, new MultiplicationRule());
        addThirdRules();

    }

    private static void addThirdRules() {
        factory.put(NO_REPEAT_VALUE_RULE, new NoRepeatValueRule());
        factory.put(CLOSE_DIAGONAL_CIRCUIT_RULE, new ConectionInsideBoardRule());
        factory.put(NUMBER_DIAGONALS_RULE, new ConectionInsideBoardRule());
    }

    public GenericRule createRule(String ruleName) {
        return (GenericRule) factory.get(ruleName);
    }

    private static void putRules() {
        addFirstRules();
        addSecondRules();
    }

    private static void addFirstRules() {
        factory.put(CONECTIONS_IN_CELL_RULE, new CellHasValidConectionsRule());
        factory.put(CONECTIONS_INSIDE_BOARD_RULE, new ConectionInsideBoardRule());

    }
}

