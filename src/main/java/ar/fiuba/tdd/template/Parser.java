package ar.fiuba.tdd.template;


import ar.fiuba.tdd.template.board.cell.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Parser {

    private int height;
    private Integer width;
    private ArrayList<Cell> clues = new ArrayList<>();
    private ArrayList<Cell> solution = new ArrayList<>();
    private ArrayList<String> rules = new ArrayList<>();

    // Class designed to parse JSON data

    public void decodeJson() {
        // Getting board data from the json file
        JSONParser parser = new JSONParser();
        try {
            // Uses bufferedReader to avoid reliance on default encoding
            String jsonFileName = "src/json/Board.json";
            BufferedReader inJsonFile = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFileName), "UTF8"));
            Object fileObject = parser.parse(inJsonFile);
            JSONObject jsonObject = (JSONObject) fileObject;

            // Read Board Width
            this.width = readWidth(jsonObject).intValue();

            // Read Board Height
            this.height = readHeight(jsonObject).intValue();

            readRules(jsonObject);
            readElements(jsonObject, "clues");
            readElements(jsonObject, "solution");

            inJsonFile.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

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

        JSONArray cellContents = (JSONArray) jsonObject.get(id);

        for (JSONObject cellClue : (Iterable<JSONObject>) cellContents) { // for every cell
            int positionX = ((Long) cellClue.get("x")).intValue();
            int positionY = ((Long) cellClue.get("y")).intValue();
            Cell newCell = new Cell(positionX, positionY); // create a single cell

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

}
