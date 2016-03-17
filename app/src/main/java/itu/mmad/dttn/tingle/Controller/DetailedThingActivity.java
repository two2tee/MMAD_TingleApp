package itu.mmad.dttn.tingle.Controller;

import android.support.v4.app.Fragment;

import itu.mmad.dttn.tingle.Controller.Fragments.ListFragment;
import itu.mmad.dttn.tingle.Controller.Fragments.ThingFragment;

/**
 * Activity to display detailed things and list
 */
public class DetailedThingActivity extends GenericFragmentActivity {
    @Override
    protected Fragment createPortraitFragment() {
        return new ThingFragment();
    }

    @Override
    protected Fragment createLeftFragment() {
        return new ThingFragment();
    }

    @Override
    protected Fragment createRightFragment() {
        return new ListFragment();
    }



}
