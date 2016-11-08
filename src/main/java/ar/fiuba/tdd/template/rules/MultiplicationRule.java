package ar.fiuba.tdd.template.rules;

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

