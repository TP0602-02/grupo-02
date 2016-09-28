package ar.fiuba.tdd.template.rules;

/**
 * Created by matiaskamien on 28/09/16.
 */
public class NoRepeatNumberInVerticalValidationRule extends NoRepeatNumberInRegionValidationRule {

    public NoRepeatNumberInVerticalValidationRule() {
        this.nextRule = new NullRule();
        this.iterators.add(new AboveIterator());
        this.iterators.add(new BelowIterator());
    }

}
