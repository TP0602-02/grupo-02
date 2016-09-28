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
        // TODO: generalize this method for any game
        ArrayList<Object> boardElements = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            // Uses bufferedReader to avoid reliance on default encoding
            String jsonFileName = "src/json/Board.json";
            BufferedReader inJsonFile = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFileName), "UTF8"));
            Object fileObject = parser.parse(inJsonFile);
            JSONObject jsonObject = (JSONObject) fileObject;

            // Prints width
            Long width = (Long) jsonObject.get("width");
            boardElements.add(width);
            System.out.println(Long.toString(width));

            // Prints height
            Long height = (Long) jsonObject.get("height");
            boardElements.add(height);
            System.out.println(Long.toString(height));

            JSONArray cellContents = (JSONArray) jsonObject.get("cellContents");
            Iterator<JSONObject> cellContentsIterator = cellContents.iterator();

            while (cellContentsIterator.hasNext()) {
                Object cellType = cellContentsIterator.next();
                System.out.println(cellType);
                boardElements.add(cellType.toString());
            }
            inJsonFile.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return boardElements;
    }
}
