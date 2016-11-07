package ar.fiuba.tdd.template.puzzle.aggregators;

import ar.fiuba.tdd.template.board.cell.controller.CellController;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by alazraqui on 18/10/2016.
 */
public class AggregatorFactory {
    private static Properties factory;
    private static AggregatorFactory instance;

    public static final String SIMPLE_AGGREGATOR = "Simple aggregator";
    public static final String CONNECTION_AGGREGATOR = "Connection aggregator";

    public AbstractAgreggator createAggregator(String aggregator) {
        return (AbstractAgreggator) factory.get(aggregator);
    }

    private static void initFactory() {
        factory = new Properties();
        putConstants();
    }

    private static void putConstants() {
        factory.put(SIMPLE_AGGREGATOR, new Aggregator());
        factory.put(CONNECTION_AGGREGATOR, new AggregatorWithConnections());
    }

    private AggregatorFactory(){

    }

    public static AggregatorFactory getFactory() {
        if (instance == null) {
            instance = new AggregatorFactory();
            initFactory();
        }
        return instance;
    }

}
