package ar.fiuba.tdd.template;

import java.util.Random;

/**
 * Created by Colo on 23/09/2016.
 */
public class Facade {
    private Object controller;
    private static Facade Instance;
    public static Facade getInstance() {
        return Instance;
    }

    public static Facade createInstance(Object controller) {
        if (Instance == null) {
            Instance = new Facade(controller);
        }
        return Instance;
    }

    private Facade(Object controller){
        this.controller=controller;
    }

    public static int getBoardSize() {
        return 9;
    }

    public static CellContent getCellContent(int counterX, int counterY) {
        Random rand=new Random();
        int x=rand.nextInt(2);
        if(x==0){
            return new BlankCell();
        }else{
            if(x==1){
                return new BlackCell();
            }else{
                return new ClueCell(x=rand.nextInt(9));
            }
        }
    }

    public void comprobarJugada() throws Exception {
        Random rand=new Random();
        int x=rand.nextInt(2);
        if(x==0){
            throw new Exception();
        }
    }
}
