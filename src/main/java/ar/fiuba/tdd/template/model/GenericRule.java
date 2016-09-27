package ar.fiuba.tdd.template.model;

import sun.net.www.content.text.Generic;

/**
 * Created by alazraqui on 25/09/2016.
 */
public abstract class GenericRule {

    protected GenericRule nextRule;

    public GenericRule() {
        this.nextRule = new NullRule();
    }

    public void setNextRule(GenericRule nextRule) {
        this.nextRule = nextRule;
    }

    public abstract boolean validate(Board board, Cell cell, int numberToAdd);
}
