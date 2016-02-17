package itu.mmad.dttn.tingle.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;

import itu.mmad.dttn.tingle.Controller.Fragments.TingleFragment;
import itu.mmad.dttn.tingle.Model.InMemoryRepository;
import itu.mmad.dttn.tingle.Model.Interfaces.IRepository;
import itu.mmad.dttn.tingle.Model.Thing;
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
