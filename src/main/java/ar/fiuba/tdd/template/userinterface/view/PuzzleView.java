package ar.fiuba.tdd.template.userinterface.view;


import ar.fiuba.tdd.template.board.cell.controller.CellController;
import ar.fiuba.tdd.template.board.cell.model.*;
import ar.fiuba.tdd.template.board.cell.view.CellView;
import ar.fiuba.tdd.template.userinterface.controller.Facade;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * Created by Colo on 23/09/2016.
 */
public class PuzzleView extends JFrame {
    public static final int cellViewDimension = 40;
    public static final int boardInitialPositionPixelX = 200;
    public static final int boardInitialPositionPixelY = 300;
    public static final int screenHeight = 768;
    public static final int screenLenght = 1024;
    private int width;
    private int height;
    private final HomeView menu;
    private ArrayList<ArrayList<CellView>> boardView;
    private ArrayList<CellController> cellControllers;

    public PuzzleView(int width, int height) {
        this.width = width;
        this.height = height;
        setBackground(Color.gray);
        this.cellControllers = new ArrayList<>();
        this.getContentPane().setPreferredSize(new Dimension(screenLenght, screenHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.menu = new HomeView(screenLenght, screenHeight, this);
        menu.createMenu();
        this.add(menu);
        this.pack();
    }


    private void createBoardView(String game) {
        //int boardSize = Facade.getBoardSize();
        initBoardDimensions();
        this.addTitle(game);
        for (int column = 0; column < width; ++column) {
            for (int row = 0; row < height; ++row) {
                int positionCellInitialPixelX = column * cellViewDimension + boardInitialPositionPixelX;
                int positionCellInitialPixelY = row * cellViewDimension + boardInitialPositionPixelY;
                addField(simulateCreationOfCellContent(), positionCellInitialPixelX, positionCellInitialPixelY, column, row);
            }
        }
        JLabel label = new JLabel("");
        add(label);
    }

    private CellContent simulateCreationOfCellContent() {
        Random rand = new Random();
        int numberForSimulateCell = rand.nextInt(3);
        switch (numberForSimulateCell) {
            case 0:
                return new ValueContent(rand.nextInt(9));
            case 1:
                BlackContent black = new BlackContent();
                black.setDef(new BlackContent.DefValue<String>() {
                    @Override
                    public String getDefValue() {
                        return "black";
                    }
                });
                return black;
            case 2:
                return new ClueContent(rand.nextInt(20));
            default:
        }
        return null;
    }

    private void initBoardDimensions() {
        boardView = new ArrayList<>();
        for (int dimen = 0; dimen < width; ++dimen) {
            ArrayList<CellView> column = new ArrayList<CellView>();
            boardView.add(dimen, column);
            for (int row = 0; row < height; ++row) {
                column.add(new CellView());
            }
        }
    }

    private void addTitle(String game) {
        JLabel titleLabel = new JLabel(game);
        titleLabel.setBounds(boardInitialPositionPixelX, boardInitialPositionPixelY - 200, 300, 100);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 70));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleLabel);
    }

    private void addField(CellContent cellContent, int positionPixelX, int positionPixelY, int column, int row) {
        Cell cell = new Cell(column, row);
        cell.setContent(cellContent);
        CellView cellVIew = new CellView(String.valueOf(cellContent.getValue()));
        cellVIew.setBounds(positionPixelX, positionPixelY, cellViewDimension, cellViewDimension);
        getContentPane().add(cellVIew);
        boardView.get(column).add(row, cellVIew);
        CellController cellController = new CellController();
        cellController.attachElements(cellVIew, cell);
        cellControllers.add(cellController);
    }

    public void showVisu() {
        this.setVisible(true);
    }

    public void startGame(String game) {
        menu.setVisible(false);
        this.remove(menu);
        // this.pack();
        Facade.getInstance().setGame(game);
        this.createBoardView(game);
        //this.pack();
    }
}
