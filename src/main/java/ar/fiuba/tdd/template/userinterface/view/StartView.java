package ar.fiuba.tdd.template.userinterface.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Properties;
import javax.swing.*;

public class StartView extends JFrame {
    private static final int screenHeight = 768;
    private static final int screenWidth = 1024;
    private static final int WIDTH_BUTTON = 200;
    private static final int HEIGHT_BUTTON = 50;

    //***************GAMES**********************
    @SuppressWarnings("CPD-START")
    private static final String SUDOKU_NAME = "SUDOKU";
    private static final String KAKURO_NAME = "KAKURO";
    private static final String COUNTRY_ROAD_NAME = "COUNTRY ROAD";
    private static final String INSHI_NO_HEYA_NAME = "INSHI NO HEYA";
    private static final String SLITHERLINK_NAME = "SLITHERLINK";
    private static final String NUMBERLINK_NAME = "NUMBERLINK";
    private static final String GOKIEN_NANAME_NAME = "GOKIEN NANAME";
    private static final String INSHI_NO_HEYA_PLAYS = "INSHI NO HEYA WITH PLAYS";
    private static final String RIPPLE_EFFECT = "RIPPLE EFFECT";
    private static final String COUNTRY_ROAD_PLAYS = "COUNTRY ROAD WITH PLAYS";

    //**************************************************

    private StartGameListener listener;

    //**************GAME JSON FILES*************************
    private static final String SUDOKU_FILE = "Sudoku.json";
    private static final String KAKURO_FILE = "Kakuro.json";
    private static final String INSHI_NO_HEYA_FILE = "InshiNoHeya.json";
    private static final String SLITHERLINK_FILE = "Slitherlink.json";
    private static final String NUMBERLINK_FILE = "Numberlink.json";
    private static final String GOKIEN_NANAME_FILE = "GokienNaname.json";
    private static final String COUNTRY_ROAD_FILE = "CountryRoad.json";
    private static final String COUNTRY_ROAD_FILE_AC2 = "CountryRoadConfigAc2.json";
    private static final int TITLE_POS_Y = 10;
    //*************************************************
    private static final String RIPPLE_EFFECT_FILE = "RippleEffect.json";
    // Files para la actividad 2
    private static final String INSHI_NO_HEYA_PLAYS_FILE = "InshiNoHeyaPlaysAc2.json";
    private static final String COUNTRY_ROAD_PLAYS_FILE = "CountryRoadPlaysAc2.json";

    //*************************************************
    @SuppressWarnings("CPD-END")

    public StartView(StartGameListener listener) {
        this.listener = listener;
    }

    private Properties games;

    public void start() {
        setSize(screenWidth, screenHeight);
        JLabel title = new JLabel("NIKOLI GAMES");
        title.setBounds(screenWidth / 3, TITLE_POS_Y, 300, 40);
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        add(title);
        initGames();
        createButtons();
        setLayout(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void createButtons() {
        Enumeration enumeration = games.propertyNames();
        int posXButtonGame = screenWidth / 3;
        int posYButtonGame = TITLE_POS_Y + 60;
        int spaceBetweenButtons = 15;
        while (enumeration.hasMoreElements()) {
            final String gameName = (String) enumeration.nextElement();
            JButton button = new JButton(gameName);
            button.setBounds(posXButtonGame, posYButtonGame, WIDTH_BUTTON, HEIGHT_BUTTON);
            posYButtonGame += HEIGHT_BUTTON + spaceBetweenButtons;
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
        createPlayFileButton(posXButtonGame, posYButtonGame, INSHI_NO_HEYA_PLAYS,
                INSHI_NO_HEYA_PLAYS_FILE, INSHI_NO_HEYA_NAME,INSHI_NO_HEYA_FILE);
        posYButtonGame += HEIGHT_BUTTON + spaceBetweenButtons;
        createPlayFileButton(posXButtonGame, posYButtonGame, COUNTRY_ROAD_PLAYS,
                COUNTRY_ROAD_PLAYS_FILE, COUNTRY_ROAD_NAME,COUNTRY_ROAD_FILE_AC2);
    }

    private void createPlayFileButton(int posX, int posY, String buttonName, String playsFile, String gameName,String configFile) {
        JButton buttonPlaysFile = new JButton(buttonName);
        buttonPlaysFile.setBounds(posX, posY, WIDTH_BUTTON, HEIGHT_BUTTON);
        buttonPlaysFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                StartView.this.setVisible(false);
                listener.loadPlaysForGame(playsFile, configFile, gameName);
            }
        });
        this.add(buttonPlaysFile);
    }

    @SuppressWarnings("CPD-START")

    private void initGames() {
        games = new Properties();
        games.put(SUDOKU_NAME, SUDOKU_FILE);
        games.put(KAKURO_NAME, KAKURO_FILE);
        games.put(COUNTRY_ROAD_NAME, COUNTRY_ROAD_FILE);
        games.put(INSHI_NO_HEYA_NAME, INSHI_NO_HEYA_FILE);
        games.put(SLITHERLINK_NAME, SLITHERLINK_FILE);
        games.put(GOKIEN_NANAME_NAME, GOKIEN_NANAME_FILE);
        games.put(RIPPLE_EFFECT,RIPPLE_EFFECT_FILE);
        games.put(NUMBERLINK_NAME, NUMBERLINK_FILE);
    }

    @SuppressWarnings("CPD-END")

    public interface StartGameListener {
        public void loadNewGame(String gameName, String gameFile);

        public void loadPlaysForGame(String playFile, String gameFile, String gameName);
    }
}
