package itu.mmad.dttn.tingle.Model.AsyncTasks;

import android.os.AsyncTask;

import java.util.List;

import itu.mmad.dttn.tingle.Model.Dagger2_DependencyInjection.Components.DaggerRepositoryComponent;
import itu.mmad.dttn.tingle.Model.Thing;
import itu.mmad.dttn.tingle.Model.ThingsDatabase;

/**
 * This class is allows searching items in async
 */
public class SearchAsync extends AsyncTask<String,Void,String>
{

    String response;
    String mWhat;

    @Override
    protected String doInBackground(String... params)
    {
        ThingsDatabase database = DaggerRepositoryComponent.builder().build().provideDatabase();

        mWhat= params[0];

        List<Thing> things = database.getAll();


    }


    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
    }
}
