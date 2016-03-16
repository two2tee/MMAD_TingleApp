package itu.mmad.dttn.tingle.Controller;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import itu.mmad.dttn.tingle.Controller.Fragments.ListFragment;
import itu.mmad.dttn.tingle.Controller.Fragments.TingleFragment;
import itu.mmad.dttn.tingle.Model.ThingsDatabase;
import itu.mmad.dttn.tingle.R;

/**
 * This class represents the launcher activity for tingle
 */
public class TingleActivity extends GenericFragmentActivity
implements ListFragment.ListFragmentEventListener, TingleFragment.TingleFragmentEventListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);
    }

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
     * Gets the database
     * @return database
     */
    public ThingsDatabase getDatabase(){
        return ThingsDatabase.getDatabase();
    }

    /**
     * Returns to tingle main page.
     */
    public void onBackPressed() {
        changeFragment(new TingleFragment());
    }

    /**
     * Goes to list page
     */
    public void onShowAllPressed() {
        changeFragment(new ListFragment());
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
