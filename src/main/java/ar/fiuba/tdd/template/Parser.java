package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.board.cell.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class Parser {

    private JSONParser parser;
    private JSONObject boardFile;
    private JSONObject playsFile;

    private int height;
    private int width;
    private ArrayList<Cell> clues = new ArrayList<>();
    private ArrayList<Cell> solution = new ArrayList<>();
    private ArrayList<String> rules = new ArrayList<>();

    private ArrayList<ArrayList<Cell>> regions = new ArrayList<>();
    private ArrayList<ArrayList<Cell>> exceptions = new ArrayList<>();

    private ArrayList<Cell> plays = new ArrayList<>();

    public Parser() {
        parser = new JSONParser();
    }

    // Obtains both files as JSONObjects to work on
    private void readFile(String boardFileName, String playsFileName) {
        try {
            // Uses bufferedReader to avoid reliance on default encoding
            BufferedReader boardJsonFile = new BufferedReader(new InputStreamReader(new FileInputStream(boardFileName), "UTF8"));
            BufferedReader playsJsonFile = new BufferedReader(new InputStreamReader(new FileInputStream(playsFileName), "UTF8"));
            Object boardFileObject = parser.parse(boardJsonFile);
            Object playsFileObject = parser.parse(playsJsonFile);
            this.boardFile = (JSONObject) boardFileObject;
            this.playsFile = (JSONObject) playsFileObject;
            boardJsonFile.close();
            playsJsonFile.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void decodeJson() {
        readFile("src/json/Board.json", "src/json/Plays.json");

        // Board configuration
        this.width = readWidth(this.boardFile).intValue();
        this.height = readHeight(this.boardFile).intValue();
        readRules(this.boardFile);
        readElements(this.boardFile, "clues");
        readElements(this.boardFile, "solution");
        readRegions(this.boardFile);

        // Automatic plays
        readPlays();
    }

    private static Long readWidth(JSONObject jsonObject) {
        return (Long)jsonObject.get("width");
    }

    private static Long readHeight(JSONObject jsonObject) {
        return (Long)jsonObject.get("height");
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public ArrayList<Cell> getClues() {
        return this.clues;
    }

    public ArrayList<Cell> getSolution() {
        return this.solution;
    }

    public ArrayList<Cell> getPlays() {
        return this.plays;
    }

    public ArrayList<String> getRules() {
        return this.rules;
    }

    private void readRules(JSONObject jsonObject) {
        JSONArray rulesContents = (JSONArray) jsonObject.get("rules");
        for (JSONObject rule : (Iterable<JSONObject>) rulesContents) { // for every rule
            //System.out.print(rule.get("rule").toString());
            rules.add(rule.get("rule").toString());
        }
    }

    private void readElements(JSONObject jsonObject, String id) {

        CellFactory cellFactory = new CellFactory();
        JSONArray cellContents = (JSONArray) jsonObject.get(id);

        for (JSONObject cellClue : (Iterable<JSONObject>) cellContents) { // for every cell
            int positionX = ((Long) cellClue.get("x")).intValue();
            int positionY = ((Long) cellClue.get("y")).intValue();
            //TODO en el archivo debe decir si son CELL SINGLE VLAUES O MULTIPLES
            String tipoDeCeldaLeidoDelArchivo = CellFactory.CELL_SINGLE_VALUE;
            Cell newCell = cellFactory.createCell(tipoDeCeldaLeidoDelArchivo,positionX,positionY);
            // create a single cell

            JSONArray contentData = (JSONArray) cellClue.get("content"); // start parsing the clues

            for (JSONObject contentsJson : (Iterable<JSONObject>) contentData) { // for every clue
                // the first value goes below, the second value above
                Long value = (Long) contentsJson.get("value");
                createContent(newCell, id, value.intValue());
            }

            if (id.equals("clues")) {
                clues.add(newCell);
            } else if (id.equals("solution")) {
                solution.add(newCell);
            }
        }
    }

    private Cell createContent(Cell newCell, String id, int value) {
        if (id.equals("clues")) {
            // Clues are considered ClueContent or BlackContent
            if (value != -1) {
                ClueContent clue = new ClueContent<>(value);
                newCell.setContent(clue);
            } else {
                // if it's -1 we consider it a BlackContent
                BlackContent black = new BlackContent(new BlackContent.DefValue<String>() {
                    @Override
                    public String getDefValue() {
                        return "black";
                    }
                });
                newCell.setContent(black);
            }
        } else if (id.equals("solution")) {
            // Solutions are considered ValueContent
            ValueContent valueContent = new ValueContent<>(value);
            newCell.setContent(valueContent);
        }
        return newCell;
    }

    private void readPlays() {
        JSONArray playsContents = (JSONArray) this.playsFile.get("plays");

        for (JSONObject play : (Iterable<JSONObject>) playsContents) { // for every play

            String delimiter = "[\\[\\], ]+";
            String[] tokens = play.get("position").toString().split(delimiter);

            // TODO El primer valor en el array es un string vacio ???
            /*for (String token : tokens) {
                if (token.contentEquals("")) {
                    System.out.print("espacio");
                } else {
                    System.out.print(token);
                }
            }

            System.out.print(" " + tokens.length + " is the length of the tokenarray ");
            */
            int positionX = Integer.parseInt(tokens[1]);
            int positionY = Integer.parseInt(tokens[2]);

            Cell newCell = new CellSingleValue(positionX, positionY); // create a single cell
            //System.out.print(" The row is " + newCell.getRow() + " " + newCell.getColumn() + "\n");

            // Plays are considered ValueContent
            ValueContent valueContent = new ValueContent<>(play.get("value"));
            newCell.setContent(valueContent);
            plays.add(newCell);
        }

    }

    private void readRegions(JSONObject jsonObject) {
        JSONArray regionContents = (JSONArray) jsonObject.get("regions");

        for (JSONObject region : (Iterable<JSONObject>) regionContents) { // for every region
            ArrayList<Cell> fromToRegion = new ArrayList<>();
            ArrayList<Cell> exceptionsRegion = new ArrayList<>();

            JSONArray coordinates = (JSONArray) region.get("coord"); // read coordinates
            for (JSONObject coord : (Iterable<JSONObject>) coordinates) {
                Cell newCell = new CellSingleValue(((Long) coord.get("x")).intValue(), ((Long) coord.get("y")).intValue());
                fromToRegion.add(newCell);
            }
            regions.add(fromToRegion);

            JSONArray exceptionsRead = (JSONArray) region.get("exceptions"); // read exceptions
            for (JSONObject excep : (Iterable<JSONObject>) exceptionsRead) {
                Cell newCell = new CellSingleValue(((Long) excep.get("x")).intValue(), ((Long) excep.get("y")).intValue());
                exceptionsRegion.add(newCell);
            }
            exceptions.add(exceptionsRegion);
        }

        System.out.print(regions.size() + " " + exceptions.size());
    }

    public void writePlayResults(ArrayList<Play> playResult) {
        JSONObject objPlay = new JSONObject();
        JSONArray jsonPlays = new JSONArray();
        JSONArray jsonValues = new JSONArray();
        int numberOfPlay = 1;

        boolean validBoard = true;
        // Creates JSON
        for ( Play play : playResult) {

            jsonPlays.add(getPlayObject(play,numberOfPlay));
            numberOfPlay++;

            jsonValues.add(getValueObject(play));

            validBoard = validBoard && play.getValidPlay();
        }

        objPlay.put("plays", jsonPlays);
        JSONObject jsonBoard = new JSONObject();
        jsonBoard.put("status", validBoard ? "valid" : "invalid");
        jsonBoard.put("values", jsonValues);
        objPlay.put("board", jsonBoard);

        writeJsonFile(objPlay);
    }

    private void writeJsonFile(JSONObject objPlay) {
        try {
            File file = new File("src/json/PlayOutput.json");
            Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println(objPlay.toJSONString());
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getPlayObject(Play play, int numberOfPlay) {
        JSONObject jsonPlay = new JSONObject();
        jsonPlay.put("number", numberOfPlay);
        jsonPlay.put("boardStatus", play.getValidPlay());
        return jsonPlay;
    }

    private JSONObject getValueObject(Play play) {
        JSONObject jsonValue = new JSONObject();
        jsonValue.put("position", "[" + play.getSelectedCell().getRow() + " ," + play.getSelectedCell().getColumn() + "]");
        jsonValue.put("value", play.getSelectedCell().getContents().get(0).getValue());
        return jsonValue;
    }
}
