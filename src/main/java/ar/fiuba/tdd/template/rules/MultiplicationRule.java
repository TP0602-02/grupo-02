package ar.fiuba.tdd.template.rules;

/**
 * Created by matiaskamien on 08/10/16.
 */
public class MultiplicationRule extends OperationRule {
    @Override
    protected void updateTotals(int value) {
        if (this.regionPartial == 0) {
            this.regionPartial = value;
        } else {
            this.regionPartial *= value;
        }
    }
}
