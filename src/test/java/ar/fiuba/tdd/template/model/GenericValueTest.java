package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.board.cell.model.GenericValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by matiaskamien on 07/10/16.
 */
public class GenericValueTest {

    private GenericValue value;

    @Before
    public void setUp() {
        this.value = new GenericValue('5');
    }

    @Test
    public void returnValueAsChar() {
        Assert.assertTrue(this.value.getValueAsChar() == '5');
    }

    @Test
    public void returnValueAsInt() {
        Assert.assertTrue(this.value.getValueAsInt() == 5);
    }
}
