package ar.fiuba.tdd.template;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is just a template project\n ");
        Visualization visu= new Visualization(Facade.createInstance(new Object()));
        visu.showVisu();
    }
}
