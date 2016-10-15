package ar.fiuba.tdd.template.entity;

/**
 * Created by Nicolas on 28/9/2016.
 */
public abstract class BaseController<T, E> {

    protected T view;
    protected E model;

    public void attachElements(T view, E model) {
        this.view = view;
        this.model = model;
        elementsAttached(this.view, this.model);
    }

    public abstract void elementsAttached(T view, E model);

    public T getView() {
        return view;
    }

    public E getModel() {
        return model;
    }



}
