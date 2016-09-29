package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.rules.finders.CellsInColumnFinder;
import ar.fiuba.tdd.template.rules.finders.CellsInRowFinder;
import ar.fiuba.tdd.template.rules.validators.NoRepeatNumberValidator;

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
        factory.put(DOESNT_REPEAT_NUMBER_IN_ROW, new Rule(new CellsInRowFinder(), new NoRepeatNumberValidator()) {
        });
        factory.put(DOESNT_REPEAT_NUMBER_IN_COLUMN,new Rule(new CellsInColumnFinder(),new NoRepeatNumberValidator()));
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