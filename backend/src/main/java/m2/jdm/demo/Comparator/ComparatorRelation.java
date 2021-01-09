package m2.jdm.demo.Comparator;

import m2.jdm.demo.Models.Relation;

import java.util.Comparator;

public class ComparatorRelation implements Comparator<Relation> {

    @Override
    public int compare(Relation o1, Relation o2) {
        return o2.getPoids() - o1.getPoids();
    }
}
