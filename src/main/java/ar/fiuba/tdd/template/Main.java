package ar.fiuba.tdd.template;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is just a template project\n ");
        Facade.createInstance(new Object());
        Visualization visu = new Visualization();
        visu.showVisu();
    }
}
