package itu.mmad.dttn.tingle.model.Searching;

import java.util.List;

import itu.mmad.dttn.tingle.model.Interfaces.ISort;
import itu.mmad.dttn.tingle.model.Thing;

/**
 * Abstract class containing methods common in sorting classes
 */
public abstract class GenericSort {

    protected ISort.type sortType;


    protected void exch(Thing[] items, int i, int j) {
        Thing thing = items[i];
        items[i] = items[j];
        items[j] = thing;
    }

    protected boolean less(Thing x, Thing y) {
        switch (sortType) {
            case SORT_WHAT:
                return (x.getWhat().toLowerCase().trim().compareTo(y.getWhat().toLowerCase().trim()) < 0);

            case SORT_WHERE:
                return (x.getWhere().toLowerCase().trim().compareTo(y.getWhere().toLowerCase().trim()) < 0);

            case SORT_DATE:
                return (x.getDate().compareTo(y.getDate()) < 0);
            default:
                return false; //impossible

        }
    }


    public boolean isSorted(List<Thing> list) {
        for (int i = 1; i < list.size(); i++) {
            if (less(list.get(i), list.get(i - 1))) return false;
        }
        return true;
    }
}
