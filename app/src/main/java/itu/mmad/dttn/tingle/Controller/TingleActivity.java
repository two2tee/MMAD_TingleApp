package itu.mmad.dttn.tingle.Controller;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import itu.mmad.dttn.tingle.Controller.Fragments.ListFragment;
import itu.mmad.dttn.tingle.Controller.Fragments.TingleFragment;
import itu.mmad.dttn.tingle.Model.Dagger2_DependencyInjection.Components.DaggerRepositoryComponent;
import itu.mmad.dttn.tingle.Model.Dagger2_DependencyInjection.Components.RepositoryComponent;
import itu.mmad.dttn.tingle.Model.Dagger2_DependencyInjection.Modules.RepositoryModule;
import itu.mmad.dttn.tingle.Model.ThingsDatabase;
import itu.mmad.dttn.tingle.R;

/**
 * This class represents the launcher activity for tingle
 */
public class TingleActivity extends FragmentActivity
implements ListFragment.OnBackPressedListener, TingleFragment.OnShowAllPressedListener{


    ThingsDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);

        setFragment();

        //Setting dependency injection for database and apply
        RepositoryComponent component = DaggerRepositoryComponent.builder()
                .repositoryModule(new RepositoryModule()).build();

        database = component.provideDatabase();

    }


    private void setFragment() {


        //Landscape mode
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Fragment fragmentLeft = new TingleFragment();
            Fragment fragmentRight = new ListFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_left, fragmentLeft)
                    .replace(R.id.fragment_container_right,fragmentRight)
                    .commit();
        }

        //Portrait mode
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Fragment fragment = new TingleFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

        }
    }

    /**
     * Sets the current fragment with a given fragment.
     * NOTE: Support library of fragments are used.
     * @param fragment Fragment
     */
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

    public ThingsDatabase getDatabase(){
        return database;
    }
}
