package itu.mmad.dttn.tingle.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import itu.mmad.dttn.tingle.Controller.Fragments.TingleFragment;
import itu.mmad.dttn.tingle.R;

/**
 * This class represents the launcher activity for tingle
 */
public class TingleActivity extends FragmentActivity {

    Fragment mFragment;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);

        fm = getSupportFragmentManager();
        mFragment = fm.findFragmentById(R.id.fragment_container);

        setFragment(new TingleFragment());

    }

    /**
     * Sets the current fragment with a given fragment.
     * NOTE: Support library of fragments are used.
     * @param fragment Fragment
     */
    public void setFragment(Fragment fragment) {
        if (fragment == null || mFragment == null)
        {
            mFragment = new TingleFragment();
        }

        mFragment = fragment;

        fm.beginTransaction()
                .add(R.id.fragment_container,fragment)
                .commit();

    }
}
