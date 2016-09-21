package ar.fiuba.tdd.template;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is just a template project\n ");

        int height = 3;
        int width = 5;
        Board board = new Board(height, width);
        System.out.println("The number of rows is " + board.getHeight() + "\n");
        System.out.println("The number of columns is " + board.getWidth() + "\n");

        for (int i =  0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.println("Value row " + i + " col " + j + " is " + board.getValue(i, j) + "\n");
            }
        }

    }
}
