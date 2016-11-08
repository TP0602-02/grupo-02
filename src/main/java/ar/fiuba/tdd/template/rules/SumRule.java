package ar.fiuba.tdd.template.rules;

public class SumRule extends OperationRule {

    public SumRule() {
    }

    @Override
    protected int getNeutralNumberForOperation() {
        return 0;
    }

    @Override
    protected void updateTotals(int value) {
        this.regionPartial += value;
    }
}
