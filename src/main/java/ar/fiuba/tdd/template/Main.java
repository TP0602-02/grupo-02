package ar.fiuba.tdd.template;


import ar.fiuba.tdd.template.userinterface.controller.Facade;
import ar.fiuba.tdd.template.userinterface.view.Visualization;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is just a template project\n ");
        Facade.createInstance(new Object());
        Visualization visu = new Visualization();
        visu.showVisu();
    }
}
