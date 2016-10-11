package ar.fiuba.tdd.template.userinterface.view;


import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.CellSingleValue;
import ar.fiuba.tdd.template.board.cell.model.ValueContent;
import ar.fiuba.tdd.template.board.cell.view.CellView;
import ar.fiuba.tdd.template.puzzle.Puzzle;
import ar.fiuba.tdd.template.userinterface.controller.Facade;

import java.awt.*;
import java.util.ArrayList;
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
    private final Puzzle puzzle;
    private final HomeView menu;
    private int width;
    private int height;
    private ArrayList<ArrayList<CellView>> boardView;
    private Container container;
    private ArrayList<Cell> initialCells;

    public PuzzleView(int height, int width, ArrayList<Cell> initialCells, Puzzle puzzle) {
        this.puzzle = puzzle;
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
        createBoardView("Sudoku");
       /*  menu.createMenu();
        this.add(menu);
        this.pack();*/
    }

    private void createBoardView(String game) {
        //int boardSize = Facade.getBoardSize();
        initBoardDimensions();
        setInitialsCells();
        setCellsBorder();
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

    private void setCellsBorder() {
        for (ArrayList<CellView> filas : boardView) {
            for (CellView cellView : filas) {
                cellView.refreshBorders();
            }
        }
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
        addField(cell, positionCellInitialPixelX,
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
        Cell cell = new CellSingleValue(row, column);
        CellContent content = new ValueContent("");
        cell.setContent(content);
        addField(cell, positionCellInitialPixelX, positionCellInitialPixelY, column, row);
    }


    private void addTitle(String game) {
        JLabel titleLabel = new JLabel(game);
        titleLabel.setBounds(boardInitialPositionPixelX, boardInitialPositionPixelY - 200, 300, 100);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 70));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(titleLabel);
    }

    private void addField(Cell cell, int positionPixelX, int positionPixelY, int column, int row) {
        // Cell cell = new Cell(column, row);
        //cell.setContent(cellContent);
        //String data="<html><center><b>5</b><br>10  15<br><center><b>20</b>"; // ESTO FUNCIONA PARA LAS CLUES MULTIPLES
        CellView cellVIew = new CellView();
        if (!cell.isEditable()) {
            cellVIew.setBackground(Color.RED);
            if (cell.getContents().size() > 1) {
                cellVIew.setFont(new Font("Serif", Font.PLAIN, 10));
            }
            //String data=getLabelView(cell);
            String data = cell.getContents().get(0).getValue();
            cellVIew.setText(data);
        }
        setCellViewBorder(cellVIew, cell);
        cellVIew.setBounds(positionPixelX, positionPixelY, cellViewDimension, cellViewDimension);
        CellView previousCellView = boardView.get(column).get(row);
        if (previousCellView != null) {
            container.remove(boardView.get(column).remove(row));
        }
        boardView.get(column).add(row, cellVIew);
        container.add(cellVIew, column * height + row);
    }

    private void setCellViewBorder(CellView cellVIew, Cell cell) {
        ArrayList<Region> regiones = puzzle.getCellRegion(cell);
        for (Region r : regiones) {
            hasNeighbour(0, 1, cell, cellVIew, r);
            hasNeighbour(0, -1, cell, cellVIew, r);
            hasNeighbour(-1, 0, cell, cellVIew, r);
            hasNeighbour(1, 0, cell, cellVIew, r);
        }
    }

    private boolean hasNeighbour(int xmove, int ymove, Cell cell, CellView cellVIew, Region region) {
        int neighbourColumn = cell.getColumn() + xmove;
        int neighbourRow = cell.getRow() + ymove;
        if (neighbourColumn >= 0 && neighbourRow >= 0 && neighbourColumn <= 8 && neighbourRow <= 8) {
            Cell cellvecina = puzzle.getCell(neighbourRow, neighbourColumn);
            cellVIew.borderSetter(xmove, ymove, region.cellBelongsToRegion(cellvecina));
        } else {
            cellVIew.borderSetter(xmove, ymove, false);
        }
        return false;
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


    public String getLabelView(Cell cell) {
        String data;
        if (cell.getContents().size() > 1) {
            data = "<html><center><b>";
            int listSize = cell.getContents().size();
            for (int i = 0; i < listSize; i++) {
                if (i != 1) {
                    data = data.concat(cell.getContents().get(i).getValue());
                } else {
                    data = data.concat(cell.getContents().get(i).getValue() + "  " + cell.getContents().get(i + 1).getValue());
                    i++;
                }
                data = data.concat("</b></br>");
            }
        } else {
            data = cell.getContents().get(0).getValue();
        }
        return data;
    }
}
