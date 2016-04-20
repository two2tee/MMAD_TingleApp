package itu.mmad.dttn.tingle.model.Searching;

import itu.mmad.dttn.tingle.model.Interfaces.ISort;
import itu.mmad.dttn.tingle.model.Thing;

/**
 * Algorithm mergesort used to sort items.
 * Taken from: Algorithms Fourth edition - Robert Sedgewick | Kevin Wayne
 * Selection sort is used instead
 */
@Deprecated
public class MergeSort extends GenericSort implements ISort {

    /**
     * Sort a list based on sort variable type (eg date, where or what)
     * List is sorted with merge sort algorithm
     *
     * @param items list
     * @param t     what sorting is based on
     */
    public void sort(Thing[] items, type t) {

        sortType = t;
        sort(items, 0, items.length - 1);
    }

    private void sort(Thing[] items, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;

        //sorting recursively
        sort(items, lo, mid);
        sort(items, mid + 1, hi);
        merge(items, lo, mid, hi); //merge result
    }


    /**
     * This method merges by first copying into temp array and then merging back to item array
     *
     * @param items list of items
     * @param lo    low
     * @param mid   mid
     * @param hi    high
     */
    private void merge(Thing[] items, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        Thing[] temp = new Thing[items.length];

        //Copy items[lo..hi] to temp[lo..hi]
        for (int k = lo; k <= hi; k++) {
            temp[k] = items[k];
        }

        //Merge back to items[lo..hi]
        for (int k = lo; k <= hi; k++) {

            if (i > mid) {
                items[k] = temp[j++];
                continue;
            } else if (j > hi) {
                items[k] = temp[i++];
            } else if (less(temp[j], temp[i])) {
                items[k] = temp[j++];
            } else {
                items[k] = temp[i++];
            }
        }

    }
}
