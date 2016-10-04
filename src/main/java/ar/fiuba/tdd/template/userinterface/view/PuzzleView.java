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
    private Container container;
    private ArrayList<Cell> initialCells;

    public PuzzleView(int height, int width, ArrayList<Cell> initialCells) {
        this.width = width;
        this.height = height;
        this.initialCells = initialCells;
        setBackground(Color.gray);
        this.getContentPane().setPreferredSize(new Dimension(screenLenght, screenHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = new Container();

        container.setPreferredSize(new Dimension(screenLenght, screenHeight));
        this.add(container);

        this.menu = new HomeView(screenLenght, screenHeight, this);
        this.pack();
        createBoardView("sudoku");
       /*  menu.createMenu();
        this.add(menu);
        this.pack();*/
    }

    private void createBoardView(String game) {
        //int boardSize = Facade.getBoardSize();
        initBoardDimensions();
        setInitialsCells();
        this.addTitle(game);
        for (int column = 0; column < width; ++column) {
            for (int row = 0; row < height; ++row) {
                int positionCellInitialPixelX = column * cellViewDimension + boardInitialPositionPixelX;
                int positionCellInitialPixelY = row * cellViewDimension + boardInitialPositionPixelY;
            }
        }
        JLabel label = new JLabel("");
        container.add(label);
    }

    private void setInitialsCells() {
        for (Cell cell : this.initialCells) {
            addInitialCellToBoardView(cell);
        }
    }

    private void addInitialCellToBoardView(Cell cell) {
        int positionCellInitialPixelX = cell.getColumn() * cellViewDimension + boardInitialPositionPixelX;
        int positionCellInitialPixelY = cell.getRow() * cellViewDimension + boardInitialPositionPixelY;
        //TODO para juegos que permitan doble valor de celda como negra y clue hay que cambiarlo!
        addField(cell.getContents().get(0), positionCellInitialPixelX,
                positionCellInitialPixelY, cell.getColumn(), cell.getRow());
    }

    private void initBoardDimensions() {
        boardView = new ArrayList<>();
        for (int dimen = 0; dimen < width; ++dimen) {
            ArrayList<CellView> column = new ArrayList<CellView>();
            boardView.add(dimen, column);
            for (int row = 0; row < height; ++row) {
                column.add(new CellView());
                addDefaultCellView(dimen, row);
            }
        }
    }


    private void addDefaultCellView(int column, int row) {
        int positionCellInitialPixelX = column * cellViewDimension + boardInitialPositionPixelX;
        int positionCellInitialPixelY = row * cellViewDimension + boardInitialPositionPixelY;
        addField(new ValueContent(""), positionCellInitialPixelX, positionCellInitialPixelY, column, row);
    }


    private void addTitle(String game) {
        JLabel titleLabel = new JLabel(game);
        titleLabel.setBounds(boardInitialPositionPixelX, boardInitialPositionPixelY - 200, 300, 100);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 70));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(titleLabel);
    }

    private void addField(CellContent cellContent, int positionPixelX, int positionPixelY, int column, int row) {
        // Cell cell = new Cell(column, row);
        //cell.setContent(cellContent);
        CellView cellVIew = new CellView(String.valueOf(cellContent.getValue()));
        //TODO sacarlo haciendo un visitor
        if (cellContent instanceof ClueContent) {
            cellVIew.setBackground(Color.RED);
        }
        cellVIew.setBounds(positionPixelX, positionPixelY, cellViewDimension, cellViewDimension);
        CellView previousCellView = boardView.get(column).get(row);
        if (previousCellView != null) {
            container.remove(boardView.get(column).remove(row));
        }
        boardView.get(column).add(row, cellVIew);
        container.add(cellVIew, column * height + row);
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

    public CellView getCellView(int row, int column) {
        return this.boardView.get(column).get(row);
    }
}
