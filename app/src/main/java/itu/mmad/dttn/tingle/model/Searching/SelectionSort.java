package itu.mmad.dttn.tingle.model.Searching;

import itu.mmad.dttn.tingle.model.Interfaces.ISort;
import itu.mmad.dttn.tingle.model.Thing;

/**
 * Simple Selection sort method
 * Taken from: Algorithms Fourth edition - Robert Sedgewick | Kevin Wayne
 */
public class SelectionSort extends GenericSort implements ISort {

    @Override
    public void sort(Thing[] items, type t) {
        sortType = t;

        int N = items.length;

        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(items[j], items[min])) {
                    min = j;
                }
                exch(items, i, min);
            }
        }

    }

}
