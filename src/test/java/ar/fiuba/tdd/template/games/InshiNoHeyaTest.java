package ar.fiuba.tdd.template.games;

import ar.fiuba.tdd.template.Parser;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class InshiNoHeyaTest {

    private static final String INSHI_OUTPUT_FILE = "/inshinoheyaOutput.json";
    private static final String OUTPUT_ROOT = "src/json";

    @Test
    public void everyPlayIsValid() {
        File file = new File(OUTPUT_ROOT + INSHI_OUTPUT_FILE);
        if (file.exists()) {
            Parser parser = new Parser();
            ArrayList<String> playsBoardStatus = parser.decodeJsonOutput(INSHI_OUTPUT_FILE);
            boolean valid = true;
            for (String playValidity : playsBoardStatus) {
                if (playValidity.equals("invalid")) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                assert true;
            } else {
                assert false;
            }
        } else {
            System.out.print(OUTPUT_ROOT + INSHI_OUTPUT_FILE + " does not exist\n");
        }
    }

}
