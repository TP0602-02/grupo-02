package ar.fiuba.tdd.template;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Parser {

    // Class designed to parse JSON data

    public static ArrayList<Object> decodeJson() {
        // Getting board data from the json file
        ArrayList<Object> boardElements = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            // Uses bufferedReader to avoid reliance on default encoding
            String jsonFileName = "src/json/Board.json";
            BufferedReader inJsonFile = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFileName), "UTF8"));
            Object fileObject = parser.parse(inJsonFile);
            JSONObject jsonObject = (JSONObject) fileObject;

            // Get Board Width

            boardElements.add(getWidth(jsonObject));

            // Get Board Height

            boardElements.add(getHeight(jsonObject));


            // Get Clue Elements

            JSONArray cellContents = (JSONArray) jsonObject.get("clues");
            ArrayList<Object> clues = new ArrayList<>();
            Iterator<JSONObject> cellContentsIterator = cellContents.iterator();


            while (cellContentsIterator.hasNext()) {
                JSONObject cellClue = cellContentsIterator.next();
                Long positionX = (Long) cellClue.get("x");
                Long positionY = (Long) cellClue.get("y");
                clues.add(positionX);
                clues.add(positionY);

                JSONArray contentData = (JSONArray) cellClue.get("content");
                Iterator<JSONObject> contentDataIterator = contentData.iterator();
                ArrayList<Object> contents = new ArrayList<>();
                while (contentDataIterator.hasNext()) {
                    JSONObject contentsJson = contentDataIterator.next();
                    ArrayList<Object> content = new ArrayList<>();
                    String layout = (String) contentsJson.get("layout");
                    Long value = (Long) contentsJson.get("value");
                    content.add(layout);
                    content.add(value);
                    contents.add(content);
                }
                clues.add(contents);
            }
            boardElements.add(clues);
            inJsonFile.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return boardElements;
    }

    private static Long getWidth(JSONObject jsonObject) {
        // Prints width
        Long width = (Long) jsonObject.get("width");
        return width;
    }

    private static Long getHeight(JSONObject jsonObject) {
        // Prints width
        Long height= (Long) jsonObject.get("height");
        return height;
    }
}
