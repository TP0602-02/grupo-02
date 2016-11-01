package ar.fiuba.tdd.template.userinterface.view;


import ar.fiuba.tdd.template.board.cell.model.*;
import ar.fiuba.tdd.template.board.cell.view.CellView;
import ar.fiuba.tdd.template.entity.Coordinate;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Created by Colo on 23/09/2016.
 */
public class PuzzleView extends JFrame {
    public static final int cellViewDimension = 60;
    public static final int screenHeight = 1024;
    public static final int screenWidth = 1600;
    public static final int boardInitialPositionPixelX = screenWidth / 2;
    public static final int boardInitialPositionPixelY = screenHeight / 3;
    private int width;
    private int height;
    //private final HomeView menu;
    private ArrayList<ArrayList<CellView>> boardView;
    private JLayeredPane container;
    private ArrayList<Cell> initialCells;
    private ArrayList<Cell> graphicsInitialClues;

    public PuzzleView(int height, int width, ArrayList<Cell> initialCells, String gameName, ArrayList<Cell> graphicsInitialClues) {
        this.width = width;
        this.height = height;
        this.initialCells = initialCells;
        this.graphicsInitialClues = graphicsInitialClues;
        setBackground(Color.gray);
        this.getContentPane().setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = new JLayeredPane();

        container.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.add(container);
        //this.menu = new HomeView(screenWidth, screenHeight, this);
        this.pack();
        createBoardView(gameName);
       /*  menu.createMenu();
        this.add(menu);
        this.pack();*/
    }

    private void createBoardView(String game) {
        initBoardDimensions();
        setInitialsCellsWithClues(this.initialCells);
        setInitialsCellsWithClues(this.graphicsInitialClues);
        this.addTitle(game);
        for (int column = 0; column < width; ++column) {
            for (int row = 0; row < height; ++row) {
                int positionCellInitialPixelX = column * cellViewDimension + boardInitialPositionPixelX;
                int positionCellInitialPixelY = row * cellViewDimension + boardInitialPositionPixelY;
            }
        }
        //JLabel label = new JLabel("");
    }

    private void setInitialsCellsWithClues(ArrayList<Cell> cells) {
        for (Cell cell :cells) {
            addInitialCellWithCluesToBoardView(cell);
        }
    }

    private void addInitialCellWithCluesToBoardView(Cell cell) {
        int positionCellInitialPixelX = cell.getColumn() * cellViewDimension + boardInitialPositionPixelX;
        int positionCellInitialPixelY = cell.getRow() * cellViewDimension + boardInitialPositionPixelY;
        //TODO para juegos que permitan doble valor de celda como negra y clue hay que cambiarlo!
        addField(cell, positionCellInitialPixelX, positionCellInitialPixelY);
        //  addField(cell.getContents().get(0), positionCellInitialPixelX,
        //        positionCellInitialPixelY, cell.getColumn(), cell.getRow());
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
        Cell defaultCell = new CellSingleValue(new Coordinate(row, column));
        defaultCell.setContent(new ValueContent(""));
        addDefaultCell(defaultCell, positionCellInitialPixelX, positionCellInitialPixelY);
    }


    private void addTitle(String game) {
        JLabel titleLabel = new JLabel(game);
        titleLabel.setBounds(boardInitialPositionPixelX, boardInitialPositionPixelY - 200, 300, 100);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 70));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(titleLabel);
    }

    private void addDefaultCell(Cell cell, int positionPixelX, int positionPixelY) {
        CellView cellVIew = new CellView();
        cellVIew.setBounds(positionPixelX, positionPixelY, cellViewDimension, cellViewDimension);
        CellView previousCellView = boardView.get(cell.getColumn()).get(cell.getRow());
        if (previousCellView != null) {
            container.remove(boardView.get(cell.getColumn()).remove(cell.getRow()));
        }
        boardView.get(cell.getColumn()).add(cell.getRow(), cellVIew);
        container.add(cellVIew, cell.getColumn() * height + cell.getRow());
    }

    private void initLabelPropertiesToShowClues(JLabel label, Color background) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(background);
        label.setFont(new Font("Serif", Font.BOLD, 15));
        label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.black));
        label.setOpaque(true);
    }

    private void addField(Cell cell, int positionPixelX, int positionPixelY) {
        int clueWidht = cellViewDimension / cell.getContents().size();
        for (CellContent cellContent : cell.getContents()) {
            JLabel label = new JLabel();
            initLabelPropertiesToShowClues(label, cellContent.getColorRepresentation());
            if (!cellContent.isEditable()) {
                label.setText(cellContent.isShowableInBoard() ? cellContent.getValue() : "");
                calculateBoundsForCluesNotInCorners(label, cell.isEditable(), positionPixelX, positionPixelY, clueWidht);
                positionPixelX += clueWidht;
            } else {
                label.setText(((RelativeClueContent) cellContent).getClue().getValue());
                label.setForeground(Color.red);
                calculateBoundsForCluesInCorners(label, cellContent, positionPixelX, positionPixelY);
            }
            container.add(label, Integer.valueOf((cell.getContents().size() + 1)));
        }
    }

    private void calculateBoundsForCluesNotInCorners(JLabel label, boolean cellEditable,
                                                     int positionPixelX, int positionPixelY,int clueWidht) {
        label.setBounds(positionPixelX, positionPixelY, ((cellEditable) ? clueWidht / 3 : clueWidht),
                ((cellEditable) ? clueWidht / 2 : cellViewDimension));
    }

    private void calculateBoundsForCluesInCorners(JLabel label, CellContent cellContent, int positionPixelX, int positionPixelY) {
        int posX = 0;
        int posY = 0;
        switch (cellContent.getValue()) {
            case "2":
                posX = cellViewDimension;
                break;
            case "3":
                posY = cellViewDimension;
                break;
            case "4":
                posX = cellViewDimension;
                posY = cellViewDimension;
                break;
            default:
                break;
        }
        int dimensionOfClue = 20;
        label.setBounds(positionPixelX - dimensionOfClue / 2 + posX, positionPixelY - dimensionOfClue / 2 + posY,
                dimensionOfClue, dimensionOfClue);
    }

    public CellView getCellView(int row, int column) {
        return this.boardView.get(column).get(row);
    }
}
