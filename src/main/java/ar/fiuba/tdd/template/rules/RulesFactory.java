package ar.fiuba.tdd.template.rules;

import java.util.Properties;

public class RulesFactory {

    private static Properties factory;
    private static RulesFactory instance;
    private static final String DOESNT_REPEAT_NUMBER_IN_ROW = "Doesnt repeat number in row";
    private static final String DOESNT_REPEAT_NUMBER_IN_COLUMN = "Doesnt repeat number in column";
    private static final String DOESNT_REPEAT_NUMBER_IN_SQUARE_REGION = "Doesnt repeat number in square region";
    public static final String NUMBER_VALIDATION = "Number validation";

    private static void initFactory() {
        factory = new Properties();
        factory.put(DOESNT_REPEAT_NUMBER_IN_ROW, new NoRepeatValueRule());
        factory.put(DOESNT_REPEAT_NUMBER_IN_COLUMN, new NoRepeatValueRule());
        factory.put(DOESNT_REPEAT_NUMBER_IN_SQUARE_REGION, new NoRepeatValueRule());
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
}
