package ar.fiuba.tdd.template.rules;

import ar.fiuba.tdd.template.rules.finders.ICellsFinder;
import ar.fiuba.tdd.template.rules.validators.ICellsValidator;

/**
 * Created by alazraqui on 28/09/2016.
 */
public class Rule extends GenericRule {
    public Rule(ICellsFinder finder, ICellsValidator validator) {
        this.cellFinder = finder;
        this.cellValidator = validator;
        this.nextRule = new NullRule();
    }
}
