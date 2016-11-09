package ar.fiuba.tdd.template.entity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class FileWriter {

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
            validBoard &= play.getValidPlay();
        }

        objPlay.put("plays", jsonPlays);
        JSONObject jsonBoard = new JSONObject();
        jsonBoard.put("status", validBoard ? "valid" : "invalid");
        jsonBoard.put("values", jsonValues);
        objPlay.put("board", jsonBoard);

        writeJsonFile(objPlay, fileName);
    }

    @SuppressWarnings("unchecked")
    private JSONObject getPlayObject(Play play, int numberOfPlay) {
        JSONObject jsonPlay = new JSONObject();
        jsonPlay.put("number", numberOfPlay);
        jsonPlay.put("boardStatus", play.getValidPlay() ? "valid" : "invalid");
        return jsonPlay;
    }


    @SuppressWarnings("unchecked")
    private JSONObject getValueObject(Play play) {
        JSONObject jsonValue = new JSONObject();
        //se suma +1 porque la primer fila y columna considerada en el file es 1, pero en java es 0.
        jsonValue.put("position", "[" + (play.getSelectedCell().getRow() + 1) + " ," + (play.getSelectedCell().getColumn() + 1) + "]");
        jsonValue.put("value", play.getSelectedCell().getContents().get(0).getValue());
        return jsonValue;
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

}
