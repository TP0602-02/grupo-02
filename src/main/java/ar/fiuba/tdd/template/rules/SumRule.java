package ar.fiuba.tdd.template.rules;

/**
 * Created by matiaskamien on 08/10/16.
 */
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
