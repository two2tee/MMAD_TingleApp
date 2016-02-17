package itu.mmad.dttn.tingle.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import itu.mmad.dttn.tingle.Controller.Fragments.ListFragment;
import itu.mmad.dttn.tingle.Controller.Fragments.TingleFragment;
import itu.mmad.dttn.tingle.R;

/**
 * This class represents the launcher activity for tingle
 */
public class TingleActivity extends FragmentActivity
implements ListFragment.OnBackPressedListener, TingleFragment.OnShowAllPressedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);

        if(savedInstanceState == null) {
            setFragment();
        }

    }


    private void setFragment() {
        Fragment fragment = new TingleFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();

    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
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
}
