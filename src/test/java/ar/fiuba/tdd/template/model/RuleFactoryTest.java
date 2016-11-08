package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.rules.*;
import org.junit.Assert;
import org.junit.Test;

public class RuleFactoryTest {

    @Test
    public void testCreateCellHasValidConnectionsRule() {
        GenericRule rule =
                RulesFactory.getFactory().createRule(RulesFactory.CONECTIONS_IN_CELL_RULE);
        Assert.assertTrue(rule instanceof CellHasValidConectionsRule);
    }

    @Test
    public void testCreateCloseCircuitRule() {
        GenericRule rule =
                RulesFactory.getFactory().createRule(RulesFactory.CLOSE_CIRCUIT_RULE);
        Assert.assertTrue(rule instanceof CloseCircuitConnectionRule);
    }

    @Test
    public void testCreateConnectionsInsideBoardRule() {
        GenericRule rule =
                RulesFactory.getFactory().createRule(RulesFactory.CONECTIONS_INSIDE_BOARD_RULE);
        Assert.assertTrue(rule instanceof ConectionInsideBoardRule);
    }

    @Test
    public void testCreateRegionHasValidConnections() {
        GenericRule rule =
                RulesFactory.getFactory().createRule(RulesFactory.CONECTIONS_IN_REGION_RULE);
        Assert.assertTrue(rule instanceof RegionHasValidConectionsRule);
    }

    @Test
    public void testCreateMultiplicationRule() {
        GenericRule rule =
                RulesFactory.getFactory().createRule(RulesFactory.MULTIPLICATION_RULE);
        Assert.assertTrue(rule instanceof MultiplicationRule);
    }

    @Test
    public void testCreateSumRule() {
        GenericRule rule =
                RulesFactory.getFactory().createRule(RulesFactory.SUM_RULE);
        Assert.assertTrue(rule instanceof SumRule);
    }

    @Test
    public void testCreateNoRepeatValueRule() {
        GenericRule rule =
                RulesFactory.getFactory().createRule(RulesFactory.NO_REPEAT_VALUE_RULE);
        Assert.assertTrue(rule instanceof NoRepeatValueRule);
    }

    @Test
    public void testCreateNumberOfConnectionsInRegionRule() {
        GenericRule rule =
                RulesFactory.getFactory().createRule(RulesFactory.NUMBER_CONECTIONS_IN_REGION_RULE);
        Assert.assertTrue(rule instanceof NumberOfConectionsInRegionRule);
    }
}
