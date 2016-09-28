package ar.fiuba.tdd.template.rules;

/**
 * Created by matiaskamien on 27/09/16.
 */
public class NoRepeatNumberInHorizontalValidationRule extends NoRepeatNumberInRegionValidationRule {

    public NoRepeatNumberInHorizontalValidationRule() {
        this.nextRule = new NullRule();
        this.iterators.add(new LeftIterator());
        this.iterators.add(new RightIterator());
    }

}
