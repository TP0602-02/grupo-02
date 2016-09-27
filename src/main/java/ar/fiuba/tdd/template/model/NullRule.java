package ar.fiuba.tdd.template.model;

/**
 * Created by alazraqui on 27/09/2016.
 */
public class NullRule extends GenericRule {

    @Override
    public boolean validate(Board board, Cell cell, int numberToAdd) {
        return true;
    }
}
