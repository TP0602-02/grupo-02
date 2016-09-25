package ar.fiuba.tdd.template;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Colo on 23/09/2016.
 */
public class Facade {
    private static Facade Instance;
    private Object controller;

    private Facade(Object controller) {
        this.controller = controller;
    }

    public static Facade getInstance() {
        return Instance;
    }

    public static Facade createInstance(Object controller) {
        if (Instance == null) {
            Instance = new Facade(controller);
        }
        return Instance;
    }

    public static int getBoardSize() {
        return 9;
    }

    public static CellContent getCellContent(int counterX, int counterY) {
        Random rand = new Random();
        int random = rand.nextInt(2);
        if (random == 0) {
            return new BlankCell();
        } else {
            if (random == 1) {
                return new BlackCell();
            } else {
                return new ClueCell(rand.nextInt(9));
            }
        }
    }

    public void comprobarJugada() throws Exception {
        //controller.comprobarJugada();
        Random rand = new Random();
        int random = rand.nextInt(2);
        if (random == 0) {
            throw new Exception();
        }
    }

    public LinkedList<String> getGames() {
        //return controller.getGames();
        LinkedList<String> games = new LinkedList<String>();
        games.add("Sudoku");
        games.add("Kakuro");
        return games;
    }

    public void setGame(String game) {
        //controller.setGame(game);
        System.out.printf(controller.toString());
    }
}
