package ar.fiuba.tdd.template.rules.finders;

import ar.fiuba.tdd.template.rules.iterators.AboveIterator;
import ar.fiuba.tdd.template.rules.iterators.BelowIterator;

/**
 * Created by alazraqui on 28/09/2016.
 */
public class VerticalRegionFinder extends RegionFinder {
    public VerticalRegionFinder() {
        this.iterators.add(new AboveIterator());
        this.iterators.add(new BelowIterator());
    }
}
