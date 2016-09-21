package ar.fiuba.tdd.template;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is just a template project\n ");

        int height = 3;
        int width = 5;
        Board<Integer> board = new Board<>(height, width); // should we only accept n x n boards?
        System.out.println("The number of rows is " + board.getHeight() + "\n");
        System.out.println("The number of columns is " + board.getWidth() + "\n");

        for (int i =  0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                try {
                    System.out.println("Value row " + i + " col " + j + " is " + board.getValue(i, j));
                } catch (AssertionError e) {
                    System.out.println("Value row " + i + " col " + j + " is empty");
                }
            }
        }

        int row = 1;
        int column = 1;
        int newValue = 2;
        board.setValue(row, column, newValue);
        try {
            System.out.println("\nValue row " + row + " col " + column + " is " + board.getValue(row, column) + "\n");
        } catch (AssertionError e) {
            System.out.println("\nValue row " + row + " col " + column + " is empty\n");
        }

    }
}
