package ar.fiuba.tdd.template.rules;

import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by Nicolas on 27/9/2016.
 */
public class RulesFactory {

    private static Properties factory;
    private static RulesFactory instance;
    public static final String DOESNT_REPEAT_NUMBER_IN_ROW = "Doesnt repeat number in row";
    public static final String DOESNT_REPEAT_NUMBER_IN_COLUMN = "Doesnt repeat number in column";

    private static void initFactory() {
        factory = new Properties();
        factory.put(DOESNT_REPEAT_NUMBER_IN_ROW,new NoRepeatNumberInRowValidationRule());
        factory.put(DOESNT_REPEAT_NUMBER_IN_COLUMN,new NoRepeatNumberInColumnValidationRule());
    }

    public static RulesFactory getFactory() {
        if (instance == null) {
            instance = new RulesFactory();
            initFactory();
        }
        return instance;
    }

    public GenericRule createRule(String ruleName) {
        return (GenericRule) factory.getOrDefault(ruleName, new NullRule());

    }
}
