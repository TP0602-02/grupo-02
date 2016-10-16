package ar.fiuba.tdd.template.userinterface.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Properties;
import javax.swing.*;


/**
 * Created by Nicolas on 12/10/2016.
 */
public class StartView extends JFrame {
    public static final int screenHeight = 768;
    public static final int screenWidth = 1024;

    //***************GAMES**********************
    private static final String SUDOKU_NAME = "SUDOKU";
    private static final String KAKURO_NAME = "KAKURO";
    private static final String COUNTRY_ROAD_NAME = "COUNTRY ROAD";
    private static final String INSHI_NO_HEYA_NAME = "INSHI NO HEYA";
    private static final String SLITHERLINK_NAME = "SLITHERLINK";
    private static final String GOKIEN_NANAME_NAME = "GOKIEN NANAME";
    //**************************************************

    //**************GAME JSON FILES*************************
    public static final String SUDOKU_FILE = "Sudoku.json";
    public static final String KAKURO_FILE = "Kakuro.json";
    public static final String COUNTRY_ROAD_FILE = "CountryRoad.json";
    public static final String INSHI_NO_HEYA_FILE = "InshiNoHeya.json";
    public static final String SLITHERLINK_FILE = "Slitherlink.json";
    public static final String GOKIEN_NANAME_FILE = "GokienNaname.json";
    //*************************************************

    private Properties games;
    private StartGameListener listener;

    public StartView(StartGameListener listener) {
        this.listener = listener;
    }

    public void start() {
        setSize(screenWidth, screenHeight);
        JLabel title = new JLabel("NIKOLI GAMES");
        title.setBounds(screenWidth / 3, 10, 300, 40);
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);
        initGames();
        createButtons();
        setLayout(null);
        setVisible(true);

    }

    private void createButtons() {
        Enumeration enumeration = games.propertyNames();
        int posXButtonGame = screenWidth / 3;
        int posYButtonGame = screenWidth / 4;
        int buttonWidth = 200;
        int buttonHeight = 50;
        int spaceBetweenButtons = 15;
        while (enumeration.hasMoreElements()) {
            final String gameName = (String) enumeration.nextElement();
            JButton button = new JButton(gameName);
            button.setBounds(posXButtonGame, posYButtonGame, buttonWidth, buttonHeight);
            posYButtonGame += buttonHeight + spaceBetweenButtons;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    String gameFile = games.getProperty(gameName);
                    listener.loadNewGame(gameName, gameFile);
                    StartView.this.setVisible(false);
                }
            });
            this.add(button);
        }
    }

    private void initGames() {
        games = new Properties();
        games.put(SUDOKU_NAME, SUDOKU_FILE);
        games.put(KAKURO_NAME, KAKURO_FILE);
        games.put(COUNTRY_ROAD_NAME, COUNTRY_ROAD_FILE);
        games.put(INSHI_NO_HEYA_NAME, INSHI_NO_HEYA_FILE);
        games.put(SLITHERLINK_NAME, SLITHERLINK_FILE);
        games.put(GOKIEN_NANAME_NAME, GOKIEN_NANAME_FILE);
    }

    public interface StartGameListener {
        public void loadNewGame(String gameName, String gameFile);
    }
}
