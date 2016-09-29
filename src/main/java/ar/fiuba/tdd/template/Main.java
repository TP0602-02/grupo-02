package ar.fiuba.tdd.template;


import ar.fiuba.tdd.template.userinterface.controller.Facade;
import ar.fiuba.tdd.template.userinterface.view.PuzzleView;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is just a template project\n ");
        Facade.createInstance(new Object());
        PuzzleView visualization = new PuzzleView(9,9);
        visualization.showVisu();
    }
}
