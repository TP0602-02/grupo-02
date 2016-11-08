package ar.fiuba.tdd.template.model;

import ar.fiuba.tdd.template.winverificators.CloseCircuitVerificator;
import ar.fiuba.tdd.template.winverificators.FullBoardWinVerificator;
import ar.fiuba.tdd.template.winverificators.WinVerificator;
import ar.fiuba.tdd.template.winverificators.WinVerificatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class WinVerificatorFactoryTest {

    @Test
    public void testCreateFullBoardVerificator_ReturnTrue() {
        WinVerificator rule =
                WinVerificatorFactory.getFactory().createVerificator(WinVerificatorFactory.FULL_BOARD_WIN_VERIFICATOR);
        Assert.assertTrue(rule instanceof FullBoardWinVerificator);
    }

    @Test
    public void testCreateCloseCircuitVerificator_ReturnTrue() {
        WinVerificator rule =
                WinVerificatorFactory.getFactory().createVerificator(WinVerificatorFactory.CLOSE_CIRCUIT_WIN_VERIFICATOR);
        Assert.assertTrue(rule instanceof CloseCircuitVerificator);
    }

}
