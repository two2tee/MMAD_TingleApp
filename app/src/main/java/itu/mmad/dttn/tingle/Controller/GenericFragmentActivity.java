package itu.mmad.dttn.tingle.Controller;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import itu.mmad.dttn.tingle.Controller.Fragments.ListFragment;
import itu.mmad.dttn.tingle.Controller.Fragments.TingleFragment;
import itu.mmad.dttn.tingle.R;

/**
 * Generic fragment activity used to reduce code redundancy
 */
public abstract class GenericFragmentActivity extends FragmentActivity {
    //Portrait mode
    protected abstract Fragment createPortraitFragment();

    //Landscape mode
    protected abstract Fragment createLeftFragment();
    protected abstract Fragment createRightFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);
        setFragment();
    }

    /**
     * Sets the current fragment for portrait mode with a given fragment.
     * NOTE: Support library of fragments are used.
     * @param fragment Fragment
     */
    protected void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


    protected void setFragment() {


        //Landscape mode
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Fragment fragmentLeft = createLeftFragment();
            Fragment fragmentRight = createRightFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_left, fragmentLeft)
                    .replace(R.id.fragment_container_right,fragmentRight)
                    .commit();
        }

        //Portrait mode
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Fragment fragment = createPortraitFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}