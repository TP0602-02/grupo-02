package ar.fiuba.tdd.template.rules;

/**
 * Created by matiaskamien on 08/10/16.
 */
public class MultiplicationRule extends OperationRule {
    @Override
    protected int getNeutralNumberForOperation() {
        return 1;
    }

    @Override
    protected void updateTotals(int value) {
        this.regionPartial *= value;
    }
}

