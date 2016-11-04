package ar.fiuba.tdd.template.userinterface.view;


import ar.fiuba.tdd.template.board.Region;
import ar.fiuba.tdd.template.board.cell.model.*;
import ar.fiuba.tdd.template.board.cell.view.CellView;
import ar.fiuba.tdd.template.entity.Coordinate;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;


/**
 * Created by Colo on 23/09/2016.
 */
public class PuzzleView extends JFrame {
    public static final int cellViewDimension = 55;
    public static final int screenHeight = 900;
    public static final int screenWidth = 1300;
    public static final int boardInitialPositionPixelX = screenWidth / 3;
    public static final int boardInitialPositionPixelY = screenHeight / 3;
    private BackPressed backListener;
    private int width;
    private int height;
    //private final HomeView menu;
    private ArrayList<ArrayList<CellView>> boardView;
    private JLayeredPane container;
    private ArrayList<Cell> graphicsInitialClues;
    private String instructionGame;
    private ArrayList<Region> regionsToPaint;

    public PuzzleView(int height, int width, String gameName, ArrayList<Cell> graphicsInitialClues,
                      String instructionGame, ArrayList<Region> regions) {
        this.width = width;
        this.height = height;
        this.instructionGame = instructionGame;
        this.regionsToPaint = regions;
        this.graphicsInitialClues = graphicsInitialClues;
        setBackground(Color.gray);
        setBackground(Color.gray);
        this.getContentPane().setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = new JLayeredPane();

        container.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.add(container);
        this.pack();
        createBoardView(gameName);
        createBackButton();
    }

    private void createBackButton() {
        JButton backButton = new JButton("VOLVER AL MENU");
        backButton.setBounds(screenWidth - 200, 45, 150, 30);
        backButton.setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (backListener != null) {
                    backListener.onBackClick();
                }
            }
        });
        container.add(backButton);
    }

    private void paintRegions(ArrayList<Region> regions) {
        ArrayList<Color> colours = getColors();
        int index = 0;
        for (Region region : regions) {
            if (region.isGraficable()) {
                Color color = colours.get(index);
                index = getNextIndexColour(colours, index);
                for (Cell cell : region.getCells()) {
                    getCellView(cell.getRow(), cell.getColumn()).setBackground(color);
                }
            }
        }
    }

    private int getNextIndexColour(ArrayList<Color> colours, Integer index) {
        ++index;
        return (index >= colours.size()) ? 0 : index;

    }

    private ArrayList<Color> getColors() {
        ArrayList<Color> colours = new ArrayList<>();
        colours.add(Color.RED);
        colours.add(Color.WHITE);
        colours.add(Color.green);
        colours.add(Color.YELLOW);
        colours.add(Color.ORANGE);
        colours.add(Color.GRAY);
        colours.add(Color.GREEN);
        colours.add(Color.CYAN);
        colours.add(Color.MAGENTA);
        colours.add(Color.PINK);
        colours.add(Color.lightGray);
        return colours;
    }


    private void createBoardView(String game) {
        initBoardDimensions();
        setColumnsAndRowsNumbers();
        paintRegions(this.regionsToPaint);
        setInitialsCellsWithClues(this.graphicsInitialClues);
        this.addTitle(game);
        this.addUndo();
        for (int column = 0; column < width; ++column) {
            for (int row = 0; row < height; ++row) {
                int positionCellInitialPixelX = column * cellViewDimension + boardInitialPositionPixelX;
                int positionCellInitialPixelY = row * cellViewDimension + boardInitialPositionPixelY;
            }
        }
        createInstructionGameButton();
    }

    private void setColumnsAndRowsNumbers() {
        int posX = boardInitialPositionPixelX + cellViewDimension / 3;
        for (int row = 0; row < height; ++row) {
            JLabel label = new JLabel(String.valueOf(row));
            label.setBounds(posX, boardInitialPositionPixelY - 25, 30, 30);
            posX += cellViewDimension;
            label.setVisible(true);
            container.add(label);
        }

        int posY = boardInitialPositionPixelY + cellViewDimension / 3;
        for (int column = 0; column < width; ++column) {
            JLabel label = new JLabel(String.valueOf(column));
            label.setBounds(boardInitialPositionPixelX - 25, posY, 30, 30);
            posY += cellViewDimension;
            label.setVisible(true);
            container.add(label);
        }
    }

    private void addUndo() {
        JButton undo = Undo.getUndoButtom();
        undo.setBounds(screenWidth - 200, 85, 150, 30);
        container.add(undo);
    }


    private void createInstructionGameButton() {
        JButton buttonInstruction = new JButton("INSTRUCCIONES");
        buttonInstruction.setBounds(screenWidth - 200, 5, 150, 30);
        buttonInstruction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(container, instructionGame, "Instrucciones", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        container.add(buttonInstruction);
    }

    private void setInitialsCellsWithClues(ArrayList<Cell> cells) {
        for (Cell cell : cells) {
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
        titleLabel.setBounds(boardInitialPositionPixelX - 125, boardInitialPositionPixelY - 200, 600, 100);
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
                if (cellIsInAGraficableRegion(cell)) {
                    label.setBackground(getCellView(cell.getRow(), cell.getColumn()).getBackground());
                }
                calculateBoundsForCluesNotInCorners(label, cell.isEditable(), positionPixelX, positionPixelY, clueWidht);
                positionPixelX += clueWidht;
            } else {
                label.setText(((RelativeClueContent) cellContent).getClue().getValue());
                label.setForeground(Color.red);
                calculateBoundsForCluesInCorners(label, cellContent, positionPixelX, positionPixelY);
            }
            label.setFont(new Font("Serif", Font.BOLD, 12));
            container.add(label, Integer.valueOf((cell.getContents().size() + 1)));
        }
    }

    private boolean cellIsInAGraficableRegion(Cell cell) {
        for (Region region : this.regionsToPaint) {
            if (region.containsCell(cell) && region.isGraficable()) {
                return true;
            }
        }
        return false;
    }

    private void calculateBoundsForCluesNotInCorners(JLabel label, boolean cellEditable,
                                                     int positionPixelX, int positionPixelY, int clueWidht) {


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


    public void setBackListener(BackPressed backListener) {
        this.backListener = backListener;
    }

    public interface BackPressed {
        public void onBackClick();
    }

}
