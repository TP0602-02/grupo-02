package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.board.cell.RegionJson;
import ar.fiuba.tdd.template.board.cell.model.*;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificator;
import ar.fiuba.tdd.template.circuitverificator.CircuitVerificatorFactory;
import ar.fiuba.tdd.template.entity.Coordinate;
import ar.fiuba.tdd.template.puzzle.aggregators.AbstractAgreggator;
import ar.fiuba.tdd.template.puzzle.aggregators.AggregatorFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Parser {

    private static final String BLACK_CONTENT_VALUE = "-1";
    private static final int TOP_LEFT_CELL_REGION_INDEX = 0;
    private static final int RIGHT_BOTTOM_CELL_REGION_INDEX = 1;
    private static final String JSON_FILES_ROOT = "src/json/";
    private static final String JSON_PARSED_KEY = "Response";
    private static final String COORDINATE_X = "x";
    private static final String COORDINATE_Y = "y";
    private int height;
    private int width;
    private static final String ID = "id";
    private static final String TOTAL = "total";
    private static final String CORNER = "corner";
    private JSONParser parser;
    private JSONObject boardFile;
    private JSONObject playsFile;
    private String cellType;
    private boolean clueCellsEditables;
    private AbstractAgreggator agreggator;
    private CircuitVerificator circuitVerificator;


    private ArrayList<String> acceptedKeys = new ArrayList<>();
    private ArrayList<Cell> clues = new ArrayList<>();
    private ArrayList<Cell> graphicsInitialClues = new ArrayList<>();
    private ArrayList<String> rules = new ArrayList<>();
    private ArrayList<String> winVerificators = new ArrayList<>();
    private ArrayList<RegionJson> regionJsons;
    private ArrayList<Play> plays = new ArrayList<>();

    private CellContentJsonFactory cellContentJsonFactory;

    public Parser() {
        this.boardFile = new JSONObject();
        this.playsFile = new JSONObject();
        this.regionJsons = new ArrayList<>();
        parser = new JSONParser();
        this.cellContentJsonFactory = new CellContentJsonFactory();
    }

    @SuppressWarnings("unchecked")
    // Obtains files as JSONObjects to work on
    private void readFile(String jsonFileName, JSONObject objectParsed) {
        try {
            // Uses bufferedReader to avoid reliance on default encoding
            BufferedReader jsonFile = new BufferedReader(new InputStreamReader(
                    new FileInputStream(JSON_FILES_ROOT + jsonFileName), "UTF8"));

            objectParsed.put(JSON_PARSED_KEY, parser.parse(jsonFile));
            //System.out.print("Object Parsed: " + objectParsed.toString());
            jsonFile.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void decodeJson(String fileGame, String playsFileName) {
        readFile(fileGame, this.boardFile);

        // Board configuration
        this.width = readWidth((JSONObject) this.boardFile.get(JSON_PARSED_KEY)).intValue();
        this.height = readHeight((JSONObject) this.boardFile.get(JSON_PARSED_KEY)).intValue();
        readFirstSecions();

        readWinVerificators((JSONObject) this.boardFile.get(JSON_PARSED_KEY));
        readRegions((JSONObject) this.boardFile.get(JSON_PARSED_KEY));
        readInitialBoardContent((JSONObject) this.boardFile.get(JSON_PARSED_KEY), "initial_board_content", this.clues);
        readInitialBoardContent((JSONObject) this.boardFile.get(JSON_PARSED_KEY),
                "initial_board_grapshic_content", this.graphicsInitialClues);
        readSpecialClues((JSONObject) this.boardFile.get(JSON_PARSED_KEY));

        // Automatic plays
        //TODO desde la interfaz de usuario debe haber un boton para en vez de jeugar cargar jugadas
        if (playsFileName != null && !playsFileName.isEmpty()) {
            readFile(playsFileName, this.playsFile);
            readPlays();
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<String> decodeJsonOutput(String outputFileName) {
        JSONObject file = new JSONObject();
        readFile(outputFileName, file);
        return readOutputPlays((JSONObject) file.get(JSON_PARSED_KEY));
    }

    @SuppressWarnings("unchecked")
    private ArrayList<String> readOutputPlays(JSONObject jsonObject) {
        ArrayList<String> playsBoardStatus = new ArrayList<>();
        JSONArray playContents = (JSONArray) jsonObject.get("plays");
        for (JSONObject play : (Iterable<JSONObject>) playContents) { // for every play
            playsBoardStatus.add(play.get("boardStatus").toString());
        }
        return playsBoardStatus;
    }

    private void readFirstSecions() {
        readKeys((JSONObject) this.boardFile.get(JSON_PARSED_KEY));
        readCellContentJson((JSONObject) this.boardFile.get(JSON_PARSED_KEY));
        readRules((JSONObject) this.boardFile.get(JSON_PARSED_KEY));
        readCellType((JSONObject) this.boardFile.get(JSON_PARSED_KEY));
        readCellEditable((JSONObject) this.boardFile.get(JSON_PARSED_KEY));
        readAgreggator((JSONObject) this.boardFile.get(JSON_PARSED_KEY));
        readCircuitVerificator((JSONObject) this.boardFile.get(JSON_PARSED_KEY));
    }

    @SuppressWarnings("unchecked")
    public String getDrawerName() {
        return (String) ((JSONObject) this.boardFile.get(JSON_PARSED_KEY)).getOrDefault("drawer", "");
    }

    @SuppressWarnings("unchecked")
    private void readAgreggator(JSONObject jsonObject) {
        String agreggator = (String) jsonObject.get("agreggator");
        this.agreggator = AggregatorFactory.getFactory().createAggregator(agreggator);
    }

    @SuppressWarnings("unchecked")
    private void readCircuitVerificator(JSONObject jsonObject) {
        String circuitVerificator = (String) jsonObject.get("circuit_verificator");
        this.circuitVerificator = CircuitVerificatorFactory.getFactory().createVerificator(circuitVerificator);
    }

    @SuppressWarnings("unchecked")
    private void readCellContentJson(JSONObject jsonObject) {
        JSONArray contentsJsonArray = (JSONArray) jsonObject.get("cell_contents");
        for (JSONObject cellContentJson : (Iterable<JSONObject>) contentsJsonArray) {
            int id = getIntFromJsonObject(cellContentJson, ID);
            int value = getIntFromJsonObject(cellContentJson, TOTAL);
            CellContentJson contentJson = new CellContentJson(id, value);
            this.cellContentJsonFactory.createContent(contentJson);
        }
    }

    @SuppressWarnings("unchecked")
    private void readCellType(JSONObject jsonObject) {
        this.cellType = (String) jsonObject.get("cellType");
    }

    @SuppressWarnings("unchecked")
    private void readCellEditable(JSONObject jsonObject) {
        this.clueCellsEditables = (Boolean) jsonObject.getOrDefault("clueCellsEditables", false);
    }

    @SuppressWarnings("unchecked")
    private static Long readWidth(JSONObject jsonObject) {
        return (Long) jsonObject.get("width");
    }

    @SuppressWarnings("unchecked")
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

    public ArrayList<Play> getPlays() {
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
    private void readInitialBoardContent(JSONObject jsonObject, String id, ArrayList<Cell> cells) {

        CellFactory cellFactory = new CellFactory();
        JSONArray cellContents = (JSONArray) jsonObject.get(id);

        for (JSONObject cellClue : (Iterable<JSONObject>) cellContents) { // for every cell
            JSONArray contentData = (JSONArray) cellClue.get("content"); // start parsing the clues

            //String cellType = getCellType(contentData.size());


            Cell newCell = cellFactory.createCell("multiple_values", new Coordinate(getIntFromJsonObject(cellClue, COORDINATE_X),
                    getIntFromJsonObject(cellClue, COORDINATE_Y)));
            // create a single cell


            for (JSONObject contentsJson : (Iterable<JSONObject>) contentData) { // for every clue
                // the first value goes below, the second value above
                int idContent = getIntFromJsonObject(contentsJson, ID);

                //createContent(newCell, id, value.toString());
                newCell.addContent(this.cellContentJsonFactory.getCellContent(idContent));
                newCell.setEditable(this.clueCellsEditables);
            }
            cells.add(newCell);
        }
    }

    @SuppressWarnings("unchecked")
    private int getIntFromJsonObject(JSONObject jsonObject, String coordinateId) {
        return ((Long) jsonObject.get(coordinateId)).intValue();
    }

    @SuppressWarnings("unchecked")
    private void readSpecialClues(JSONObject jsonObject) {

        CellFactory cellFactory = new CellFactory();
        JSONArray specialClues = (JSONArray) jsonObject.get("special_clues");

        for (JSONObject cellClue : (Iterable<JSONObject>) specialClues) { // for every special cluess
            int positionX = getIntFromJsonObject(cellClue, COORDINATE_X);
            int positionY = getIntFromJsonObject(cellClue, COORDINATE_Y);
            JSONArray clues = (JSONArray) cellClue.get("clues");
            Cell newCell = cellFactory.createCell("multiple_values", new Coordinate(positionX, positionY));
            newCell.setEditable(this.isClueCellsEditables());
            for (JSONObject clueContentJson : (Iterable<JSONObject>) clues) { // for every clue
                // the first value goes below, the second value above
                int idContent = getIntFromJsonObject(clueContentJson, ID);
                int corner = getIntFromJsonObject(clueContentJson, CORNER);
                ClueContent clueContent = (ClueContent) this.cellContentJsonFactory.getCellContent(idContent);
                RelativeClueContent relativeClueContent = new RelativeClueContent(clueContent, corner);
                newCell.addContent(relativeClueContent);
            }
            this.clues.add(newCell);
        }
    }

    @SuppressWarnings("unchecked")
    private void readPlays() {
        JSONObject playResponse = (JSONObject) this.playsFile.get(JSON_PARSED_KEY);
        JSONArray playsContents = (JSONArray) playResponse.get("plays");
        CellFactory cellFactory = new CellFactory();
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
            //se resta -1 porque la primer fila y columna considerada en el file es 1, pero en java es 0.
            int positionX = Integer.parseInt(tokens[1]) - 1;
            int positionY = Integer.parseInt(tokens[2]) - 1;

            Cell newCell = cellFactory.createCell(CellFactory.CELL_SINGLE_VALUE,
                    new Coordinate(positionX, positionY)); // create a single cell
            newCell.setEditable(this.isClueCellsEditables());
            // Plays are considered ValueContent
            //ValueContent valueContent = new ValueContent(String.valueOf(play.get("value")));
            Play newPlay = new Play(newCell, String.valueOf(play.get("value")));
            //newCell.setContent(valueContent);
            plays.add(newPlay);
        }

    }

    @SuppressWarnings("unchecked")
    private void readRegions(JSONObject jsonObject) {
        JSONArray regionContents = (JSONArray) jsonObject.get("regions");

        for (JSONObject region : (Iterable<JSONObject>) regionContents) { // for every region
            // totals that are -1 mean there's no total to work with in that region
            //int total = ((Long) region.get("total")).intValue();
            int idContent = getIntFromJsonObject(region, ID);
            ArrayList<Cell> fromToRegion = getCellsFromArrayJsonCell((JSONArray) region.get("coord"));
            ArrayList<Cell> exceptionsRegion = getCellsFromArrayJsonCell((JSONArray) region.get("exceptions"));
            Cell topLeft = fromToRegion.get(TOP_LEFT_CELL_REGION_INDEX);
            Cell rightBottom = fromToRegion.get(RIGHT_BOTTOM_CELL_REGION_INDEX);
            boolean graficable = (boolean) region.getOrDefault("graficable",false);
            // Regions that have no exceptions will have x and y set to -1
            RegionJson regionJson = new RegionJson(topLeft, rightBottom, exceptionsRegion, graficable);
            regionJson.setCellContent(this.cellContentJsonFactory.getCellContent(idContent));
            this.regionJsons.add(regionJson);

        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Cell> getCellsFromArrayJsonCell(JSONArray cellJsonArray) {
        CellFactory cellFactory = new CellFactory();
        ArrayList<Cell> cells = new ArrayList<>();
        for (JSONObject jsonObject : (Iterable<JSONObject>) cellJsonArray) {
            Cell newCell = cellFactory.createCell("multiple_value",
                    new Coordinate(getIntFromJsonObject(jsonObject, COORDINATE_X), getIntFromJsonObject(jsonObject, COORDINATE_Y)));
            newCell.setEditable(this.isClueCellsEditables());
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

    public String getCellType() {
        return cellType;
    }


    public AbstractAgreggator getAgreggator() {
        return agreggator;
    }

    public CircuitVerificator getCircuitVerificator() {
        return circuitVerificator;
    }


    public boolean isClueCellsEditables() {
        return clueCellsEditables;
    }

    public ArrayList<Cell> getGraphicsInitialClues() {
        return graphicsInitialClues;
    }

    @SuppressWarnings("unchecked")
    public String getInstructionGame() {
        return (String) ((JSONObject) this.boardFile.get(JSON_PARSED_KEY)).getOrDefault("instruction", "");
    }
}
