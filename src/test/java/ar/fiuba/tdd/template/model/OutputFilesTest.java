package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.Parser;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class OutputFilesTest {

    private static final String OUTPUT_ROOT = "src/json";
    private static final String INSHI_OUTPUT_FILE = "/inshinoheyaOutput.json";
    private static final String COUNTRY_OUTPUT_FILE = "/countryroadOutput.json";


    private Parser parser;

    @Before
    public void setUp() {
        parser = new Parser();
    }

    private void getResults(String outputFileName) {
        File file = new File(OUTPUT_ROOT + outputFileName);
        if (file.exists()) {

            ArrayList<String> playsBoardStatus = parser.decodeJsonOutput(outputFileName);
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
            System.out.print(OUTPUT_ROOT + outputFileName + " does not exist\n");
        }
    }

    @Test
    public void everyInshiNoHeyaPlayIsValid() {
        getResults(INSHI_OUTPUT_FILE);
    }

   /* @Test
    public void everyCountryRoadPlayIsValid() {
        getResults(COUNTRY_OUTPUT_FILE);
    }*/

}
