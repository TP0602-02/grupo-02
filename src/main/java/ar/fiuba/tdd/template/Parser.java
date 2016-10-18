package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.board.cell.ClueJson;
import ar.fiuba.tdd.template.board.cell.RegionJson;
import ar.fiuba.tdd.template.board.cell.model.*;
import ar.fiuba.tdd.template.entity.Coordinate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class Parser {

    private static final String BLACK_CONTENT_VALUE = "-1";
    private static final int TOP_LEFT_CELL_REGION_INDEX = 0;
    private static final int RIGHT_BOTTOM_CELL_REGION_INDEX = 1;
    private static final String JSON_FILES_ROOT = "src/json/";
    private static final String JSON_PARSED_KEY = "Response";
    private JSONParser parser;
    private JSONObject boardFile;
    private JSONObject playsFile;

    private int height;
    private int width;
    private ArrayList<String> acceptedKeys = new ArrayList<>();
    private ArrayList<Cell> clues = new ArrayList<>();
    private ArrayList<String> rules = new ArrayList<>();
    private ArrayList<String> winVerificators = new ArrayList<>();
    private ArrayList<RegionJson> regionJsons;
    private ArrayList<Cell> plays = new ArrayList<>();

    private ArrayList<ClueJson> cluesJson = new ArrayList<>();

    public Parser() {
        this.boardFile = new JSONObject();
        this.playsFile = new JSONObject();
        this.regionJsons = new ArrayList<>();
        parser = new JSONParser();
    }

    @SuppressWarnings("unchecked")
    // Obtains files as JSONObjects to work on
    private void readFile(String jsonFileName, JSONObject objectParsed) {
        try {
            // Uses bufferedReader to avoid reliance on default encoding
            BufferedReader jsonFile = new BufferedReader(new InputStreamReader(
                    new FileInputStream(JSON_FILES_ROOT + jsonFileName), "UTF8"));

            objectParsed.put(JSON_PARSED_KEY, parser.parse(jsonFile));
            System.out.print("Object Parsed: " + objectParsed.toString());
            jsonFile.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void decodeJson(String fileGame, String playsFileName) {
        readFile(fileGame, this.boardFile);

        // Board configuration
        this.width = readWidth((JSONObject) this.boardFile.get(JSON_PARSED_KEY)).intValue();
        this.height = readHeight((JSONObject) this.boardFile.get(JSON_PARSED_KEY)).intValue();

        readKeys((JSONObject) this.boardFile.get(JSON_PARSED_KEY));

        readRules((JSONObject) this.boardFile.get(JSON_PARSED_KEY));
        readWinVerificators((JSONObject) this.boardFile.get(JSON_PARSED_KEY));
        readElements((JSONObject) this.boardFile.get(JSON_PARSED_KEY), "clues");
        readRegions((JSONObject) this.boardFile.get(JSON_PARSED_KEY));

        // Automatic plays
        //TODO desde la interfaz de usuario debe haber un boton para en vez de jeugar cargar jugadas
        if (playsFileName != null && !playsFileName.isEmpty()) {
            readFile(playsFileName, (JSONObject) this.playsFile.get(JSON_PARSED_KEY));
            readPlays();
        }
    }

    private static Long readWidth(JSONObject jsonObject) {
        return (Long) jsonObject.get("width");
    }

    private static Long readHeight(JSONObject jsonObject) {
        return (Long) jsonObject.get("height");
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

    public ArrayList<Cell> getPlays() {
        return this.plays;
    }

    public ArrayList<String> getAcceptedKeys() {
        return this.acceptedKeys;
    }

    public ArrayList<String> getRules() {
        return this.rules;
    }

    public ArrayList<String> getWinVerificators() {
        return this.winVerificators;
    }

    public ArrayList<RegionJson> getRegionJsons() {
        return this.regionJsons;
    }

    @SuppressWarnings("unchecked")
    private void readRules(JSONObject jsonObject) {
        JSONArray rulesContents = (JSONArray) jsonObject.get("rules");
        for (JSONObject rule : (Iterable<JSONObject>) rulesContents) { // for every rule
            rules.add(rule.get("rule").toString());
        }
    }

    @SuppressWarnings("unchecked")
    private void readWinVerificators(JSONObject jsonObject) {
        JSONArray winVerificators = (JSONArray) jsonObject.get("winVerificators");
        for (JSONObject winVerificator : (Iterable<JSONObject>) winVerificators) { // for every rule
            this.winVerificators.add(winVerificator.get("verificator").toString());
        }
    }

    @SuppressWarnings("unchecked")
    private void readCoordinates(JSONArray coordinates, ClueJson clueJson) {
        for (JSONObject coordinate : (Iterable<JSONObject>) coordinates) {
            Coordinate clueCoordinate = new Coordinate(((Long) coordinate.get("x")).intValue(),
                    ((Long) coordinate.get("y")).intValue());
            clueJson.addCoordinate(clueCoordinate);
        }
    }



    @SuppressWarnings("unchecked")
    private void readClueContents(JSONArray contentData, ClueJson clueJson, String id) {
        for (JSONObject contentsJson : (Iterable<JSONObject>) contentData) { // for every clue
            // the first value goes below, the second value above
            Long value = (Long) contentsJson.get("value");
            createContentClue(clueJson, id, value.toString());
        }
    }


/*
    @SuppressWarnings("unchecked")
    private void readElements(JSONObject jsonObject, String id) {
        JSONArray cellContents = (JSONArray) jsonObject.get(id);
        for (JSONObject cellClue : (Iterable<JSONObject>) cellContents) { // for every clue
            ClueJson clueJson = new ClueJson();
            JSONArray coordinates = (JSONArray) cellClue.get("coordinates"); // get all coordinates
            readCoordinates(coordinates, clueJson);
            JSONArray contentData = (JSONArray) cellClue.get("content"); // start parsing the contents
            readClueContents(contentData, clueJson, id);
            //String cellType = getCellType(contentData.size());
            //Cell newCell = cellFactory.createCell(cellType, new Coordinate(positionX, positionY));
            cluesJson.add(clueJson);
        }
    }
    */

    @SuppressWarnings("unchecked")
    private void readElements(JSONObject jsonObject, String id) {

        CellFactory cellFactory = new CellFactory();
        JSONArray cellContents = (JSONArray) jsonObject.get(id);

        for (JSONObject cellClue : (Iterable<JSONObject>) cellContents) { // for every cell
            ClueJson clueJson = new ClueJson();
            JSONArray coordinates = (JSONArray) cellClue.get("coordinates"); // get all coordinates
            readCoordinates(coordinates, clueJson);

            int positionX = clueJson.getCoordinates().get(0).getRow();
            int positionY = clueJson.getCoordinates().get(0).getColumn();

            //int positionX = ((Long) cellClue.get("x")).intValue();
            //int positionY = ((Long) cellClue.get("y")).intValue();

            JSONArray contentData = (JSONArray) cellClue.get("content"); // start parsing the clues

            String cellType = getCellType(contentData.size());

            Cell newCell = cellFactory.createCell(cellType, new Coordinate(positionX, positionY));
            // create a single cell

            for (JSONObject contentsJson : (Iterable<JSONObject>) contentData) { // for every clue
                // the first value goes below, the second value above
                Long value = (Long) contentsJson.get("value");

                createContent(newCell, id, value.toString());

            }

            if (id.equals("clues")) {
                clues.add(newCell);
            }


            readClueContents(contentData, clueJson, id);
            cluesJson.add(clueJson);
        }
    }

    private String getCellType(int size) {
        if (size > 1) {
            return " ";
        }
        return CellFactory.CELL_SINGLE_VALUE;
    }

    private ClueJson createContentClue(ClueJson clueJson, String id, String value) {
        if (id.equals("clues")) {
            // Clues are considered ClueContent or BlackContent
            if (!value.equals(BLACK_CONTENT_VALUE)) {
                //System.out.print(value + "\n");
                ClueContent clue = new ClueContent(value);
                clueJson.addContents(clue);
            } else {
                BlackContent black = new BlackContent();
                clueJson.addContents(black);
            }
        }
        return clueJson;
    }

    private Cell createContent(Cell newCell, String id, String value) {
        if (id.equals("clues")) {
            // Clues are considered ClueContent or BlackContent
            if (!value.equals(BLACK_CONTENT_VALUE)) {
                ClueContent clue = new ClueContent(value);
                newCell.setContent(clue);
            } else {
                BlackContent black = new BlackContent();
                newCell.setContent(black);
            }
        }
        return newCell;
    }

    @SuppressWarnings("unchecked")
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

            Cell newCell = new CellSingleValue(new Coordinate(positionX, positionY)); // create a single cell

            // Plays are considered ValueContent
            ValueContent valueContent = new ValueContent(String.valueOf(play.get("value")));
            newCell.setContent(valueContent);
            plays.add(newCell);
        }

    }

    @SuppressWarnings("unchecked")
    private void readRegions(JSONObject jsonObject) {
        JSONArray regionContents = (JSONArray) jsonObject.get("regions");

        for (JSONObject region : (Iterable<JSONObject>) regionContents) { // for every region
            // totals that are -1 mean there's no total to work with in that region
            int total = ((Long)region.get("total")).intValue();
            ArrayList<Cell> fromToRegion = getCellsFromArrayJsonCell((JSONArray) region.get("coord"));
            ArrayList<Cell> exceptionsRegion = getCellsFromArrayJsonCell((JSONArray) region.get("exceptions"));
            Cell topLeft = fromToRegion.get(TOP_LEFT_CELL_REGION_INDEX);
            Cell rightBottom = fromToRegion.get(RIGHT_BOTTOM_CELL_REGION_INDEX);
            // Regions that have no exceptions will have x and y set to -1
            this.regionJsons.add(new RegionJson(topLeft, rightBottom, exceptionsRegion, total));
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Cell> getCellsFromArrayJsonCell(JSONArray cellJsonArray) {
        CellFactory cellFactory = new CellFactory();
        ArrayList<Cell> cells = new ArrayList<>();
        for (JSONObject excep : (Iterable<JSONObject>) cellJsonArray) {
            Cell newCell = cellFactory.createCell(CellFactory.CELL_SINGLE_VALUE,
                    new Coordinate(((Long) excep.get("x")).intValue(),
                    ((Long) excep.get("y")).intValue()));
            cells.add(newCell);
        }
        return cells;
    }

    @SuppressWarnings("unchecked")
    private void readKeys(JSONObject jsonObject) {
        JSONArray keyContents = (JSONArray) jsonObject.get("keys");
        for (String key : (Iterable<String>) keyContents) { // for each key
            this.acceptedKeys.add(key);
        }
    }

    @SuppressWarnings("unchecked")
    public void writePlayResults(ArrayList<Play> playResult, String fileName) {
        JSONObject objPlay = new JSONObject();
        JSONArray jsonPlays = new JSONArray();
        JSONArray jsonValues = new JSONArray();
        int numberOfPlay = 1;

        boolean validBoard = true;
        // Creates JSON
        for (Play play : playResult) {
            jsonPlays.add(getPlayObject(play, numberOfPlay));
            numberOfPlay++;
            jsonValues.add(getValueObject(play));
            validBoard = validBoard && play.getValidPlay();
        }

        objPlay.put("plays", jsonPlays);
        JSONObject jsonBoard = new JSONObject();
        jsonBoard.put("status", validBoard ? "valid" : "invalid");
        jsonBoard.put("values", jsonValues);
        objPlay.put("board", jsonBoard);

        writeJsonFile(objPlay, fileName);
    }

    private void writeJsonFile(JSONObject objPlay, String fileName) {
        try {
            File file = new File(fileName);
            Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println(objPlay.toJSONString());
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private JSONObject getPlayObject(Play play, int numberOfPlay) {
        JSONObject jsonPlay = new JSONObject();
        jsonPlay.put("number", numberOfPlay);
        jsonPlay.put("boardStatus", play.getValidPlay());
        return jsonPlay;
    }

    @SuppressWarnings("unchecked")
    private JSONObject getValueObject(Play play) {
        JSONObject jsonValue = new JSONObject();
        jsonValue.put("position", "[" + play.getSelectedCell().getRow() + " ," + play.getSelectedCell().getColumn() + "]");
        jsonValue.put("value", play.getSelectedCell().getContents().get(0).getValue());
        return jsonValue;
    }
}
