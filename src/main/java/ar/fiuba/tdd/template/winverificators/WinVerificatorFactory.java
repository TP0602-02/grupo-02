package ar.fiuba.tdd.template.winverificators;

import java.util.Properties;

/**
 * Created by alazraqui on 16/10/2016.
 */
public class WinVerificatorFactory {
    public static final String CLOSE_CIRCUIT_WIN_VERIFICATOR = "Close circuit win verificator";
    public static final String FULL_BOARD_WIN_VERIFICATOR = "Full board win verificator";
    private static Properties factory;
    private static WinVerificatorFactory instance;

    public static WinVerificatorFactory getFactory() {
        if (instance == null) {
            instance = new WinVerificatorFactory();
            initFactory();
        }
        return instance;
    }

    private static void initFactory() {
        factory = new Properties();
        factory.put(CLOSE_CIRCUIT_WIN_VERIFICATOR, new CloseCircuitVerificator());
        factory.put(FULL_BOARD_WIN_VERIFICATOR, new FullBoardWinVerificator());
    }

    public WinVerificator createVerificator(String winVerificator) {
        return (WinVerificator) factory.get(winVerificator);
    }
}
