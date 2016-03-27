package itu.mmad.dttn.tingle.controller;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;

import itu.mmad.dttn.tingle.controller.Fragments.ListFragment;
import itu.mmad.dttn.tingle.controller.Fragments.TingleFragment;
import itu.mmad.dttn.tingle.model.database.ThingsDatabase;

/**
 * This class represents the launcher activity for tingle
 */
public class TingleActivity extends BaseActivity
implements ListFragment.ListFragmentEventListener, TingleFragment.TingleFragmentEventListener {

    @Override
    protected Fragment createPortraitFragment() {
        return new TingleFragment();
    }

    @Override
    protected Fragment createLeftFragment() {
        return new TingleFragment();
    }

    @Override
    protected Fragment createRightFragment() {
        return new ListFragment();
    }


    /**
     * Returns to tingle main page.
     */
    public void onBackPressed() {
        replacePortraitFragment(new TingleFragment());
    }

    /**
     * Goes to list page
     */
    public void onShowAllPressed() {
        replacePortraitFragment(new ListFragment());
    }

    /**
     * When add is pressed update list
     */
    @Override
    public void onItemAdded() {
        //To update list when landscape mode
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setFragment();
        }

    }


}
