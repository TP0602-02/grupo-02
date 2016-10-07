package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.rules.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Nicolas on 27/9/2016.
 */
public class RuleFactoryTest {

    @Test
    public void testCreateNoRepeatNumberInRowRule() {
        //GenericRule rule =
        //        RulesFactory.getFactory().createRule(RulesFactory.DOESNT_REPEAT_NUMBER_IN_ROW);
        //Assert.assertTrue(rule instanceof NoRepeatNumberInRowValidationRule);
    }

    @Test
    public void testCreateNullRuleWithNotValidRuleNmae() {
        GenericRule rule =
                RulesFactory.getFactory().createRule("RANDOM NAME: ABCDEFG");
        Assert.assertTrue(rule instanceof NullRule);
    }
}
