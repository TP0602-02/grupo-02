package ar.fiuba.tdd.template.rules.finders;

import ar.fiuba.tdd.template.rules.iterators.LeftIterator;
import ar.fiuba.tdd.template.rules.iterators.RightIterator;

/**
 * Created by alazraqui on 28/09/2016.
 */
public class HorizontalRegionFinder extends RegionFinder {
    public HorizontalRegionFinder() {
        this.iterators.add(new LeftIterator());
        this.iterators.add(new RightIterator());
    }
}
