package ar.fiuba.tdd.template;

import ar.fiuba.tdd.template.board.cell.model.BlackContent;
import ar.fiuba.tdd.template.board.cell.model.Cell;
import ar.fiuba.tdd.template.board.cell.model.CellContent;
import ar.fiuba.tdd.template.board.cell.model.ClueContent;
import ar.fiuba.tdd.template.puzzle.Puzzle;
import ar.fiuba.tdd.template.puzzle.PuzzleController;
import ar.fiuba.tdd.template.rules.GenericRule;
import ar.fiuba.tdd.template.rules.RulesFactory;
import ar.fiuba.tdd.template.userinterface.controller.Facade;
import ar.fiuba.tdd.template.userinterface.view.PuzzleView;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is just a template project\n ");
        //TODO DESCOMENTAR TODO PARA SIMULAR PUZZLE GENERATOR PARA EL SUDOKU, LO COMENTO PORQUE PINCHA CHECKSTYLE
       /*
        Facade.createInstance(new Object());

        int dimension = 9;

        //Simulacion de rules leidas del arxhivo y creadas por el puzzleGenerator
        GenericRule rule1 = RulesFactory.getFactory().createRule(RulesFactory.DOESNT_REPEAT_NUMBER_IN_COLUMN);
        GenericRule rule2 = RulesFactory.getFactory().createRule(RulesFactory.DOESNT_REPEAT_NUMBER_IN_ROW);
        GenericRule rule3 = RulesFactory.getFactory().createRule(RulesFactory.DOESNT_REPEAT_NUMBER_IN_SQUARE_REGION);
        GenericRule rule4 = RulesFactory.getFactory().createRule(RulesFactory.NUMBER_VALIDATION);
        ArrayList<GenericRule> arrayRules = new ArrayList<>();
        arrayRules.add(rule1);
        arrayRules.add(rule2);
        arrayRules.add(rule3);
        arrayRules.add(rule4);

        int indexOfFirstRule = 0;
        for(int index = 0; index < arrayRules.size() - 1 ; ++index ){
            arrayRules.get(index).setNextRule(arrayRules.get(index+1));
        }

        //Simulacion de clues y blacks leidas del arxhivo y creadas por el puzzleGenerator
        ArrayList<Cell> initailCells = new ArrayList<>();
        CellContent clueContent = new ClueContent(20);
        CellContent clueContent1 = new ClueContent(15);
        CellContent clueContent2 = new ClueContent(13);
        CellContent clueContent3 = new ClueContent(18);
        CellContent clueContent4 = new BlackContent(new BlackContent.DefValue<String>() {
            @Override
            public String getDefValue() {
                return "black";
            }
        });

        Cell cell = new Cell(0,0);
        Cell cell1 = new Cell(8,8);
        Cell cell2 = new Cell(0,4);
        Cell cell3 = new Cell(0,6);
        Cell cell4 = new Cell(5,5);

        cell.setContent(clueContent);
        cell1.setContent(clueContent1);
        cell2.setContent(clueContent2);
        cell3.setContent(clueContent3);
        cell4.setContent(clueContent4);

        initailCells.add(cell);
        initailCells.add(cell1);
        initailCells.add(cell2);
        initailCells.add(cell3);
        initailCells.add(cell4);


        Puzzle puzzle = new Puzzle(dimension,dimension,arrayRules.get(indexOfFirstRule),initailCells);
        PuzzleView puzzleView = new PuzzleView(dimension,dimension,initailCells);
        puzzleView.showVisu();
        PuzzleController puzzleController = new PuzzleController();
        puzzleController.attachElements(puzzleView,puzzle);
*/

    }

}
