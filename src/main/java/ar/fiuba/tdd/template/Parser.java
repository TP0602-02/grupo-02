package ar.fiuba.tdd.template;


import ar.fiuba.tdd.template.board.cell.BlackContent;
import ar.fiuba.tdd.template.board.cell.Cell;
import ar.fiuba.tdd.template.board.cell.ClueContent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Parser {

    int height;
    Integer width;
    ArrayList<Cell> boardElements = new ArrayList<>();

    // Class designed to parse JSON data

    public void decodeJson() {
        // Getting board data from the json file
        JSONParser parser = new JSONParser();
        try {
            // Uses bufferedReader to avoid reliance on default encoding
            String jsonFileName = "src/json/Sudoku.json";
            BufferedReader inJsonFile = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFileName), "UTF8"));
            Object fileObject = parser.parse(inJsonFile);
            JSONObject jsonObject = (JSONObject) fileObject;

            // Read Board Width
            this.width = readWidth(jsonObject).intValue();

            // Read Board Height
            this.height = readHeight(jsonObject).intValue();

            // Get Clue Elements
            JSONArray cellContents = (JSONArray) jsonObject.get("clues");
            Iterator<JSONObject> cellContentsIterator = cellContents.iterator();


            while (cellContentsIterator.hasNext()) { // for every cell
                JSONObject cellClue = cellContentsIterator.next();
                int positionX = ((Long)cellClue.get("x")).intValue();
                int positionY = ((Long)cellClue.get("y")).intValue();
                Cell newCell = new Cell(positionX, positionY); // create a single cell

                JSONArray contentData = (JSONArray) cellClue.get("content"); // start parsing the clues
                Iterator<JSONObject> contentDataIterator = contentData.iterator();

                while (contentDataIterator.hasNext()) { // for every clue
                    JSONObject contentsJson = contentDataIterator.next();

                    // the first value goes below, the second value above
                    Long value = (Long) contentsJson.get("value");

                    if (value.intValue() != -1) {
                        ClueContent clue = new ClueContent<>(value.intValue());
                        newCell.setContent(clue);
                    } else {
                        // if it's -1 we consider it a BlackContent
                        BlackContent black = new BlackContent();
                        newCell.setContent(black);
                    }
                }
                boardElements.add(newCell);
            }
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

    public ArrayList<Cell> getBoardElements() {
        return this.boardElements;
    }

}
