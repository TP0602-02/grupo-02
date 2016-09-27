package ar.fiuba.tdd.template.model;

/**
 * Created by alazraqui on 26/09/2016.
 */
public interface Considerable {

    boolean isConsiderable(BlackContent content);

    boolean isConsiderable(ClueContent content);

    boolean isConsiderable(ValueContent content);
}
