package itu.mmad.dttn.tingle.controller.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import itu.mmad.dttn.tingle.R;
import itu.mmad.dttn.tingle.controller.GenericFragmentActivity;
import itu.mmad.dttn.tingle.model.Thing;
import itu.mmad.dttn.tingle.model.ThingsDatabase;

/**
 * Fragment of main page.
 * NOTE: Support library of fragments are used
 * android.support.v4.app...
 */
public class TingleFragment extends Fragment {

    //EventHandler
    TingleFragmentEventListener mCallBack;
    // GUI variables
    private Button addThing;
    private Button lookUpThing;
    private Button showAll;
    private TextView lastAdded;
    private EditText whatField, whereField;
    //database
    private ThingsDatabase repository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tingle, container, false);
        repository = ((GenericFragmentActivity) getActivity()).getDatabase();

        setButtons(v);
        setTextFields(v);
        updateUI();
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //Checks if parent activity has implemented the
        //callback interface
        try {
            mCallBack = (TingleFragmentEventListener) context;
            repository = ((GenericFragmentActivity) getActivity()).getDatabase();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ListFragmentEventListener");
        }
    }

    private void setButtons(View v) {
        addThing = (Button) v.findViewById(R.id.add_button);
        addThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }

        });


        lookUpThing = (Button) v.findViewById(R.id.lookUp_button);
        lookUpThing.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (whatField.getText().length() > 0)
                {
                    String result = SearchThing(whatField.getText().toString());

                    if (result != null)
                        makeToast(getString(R.string.item_found_toast) + " " + result);
                    else makeToast(getString(R.string.item_NotFound_toast));
                } else
                {
                    makeToast(getString(R.string.no_what_specified));
                }
            }
        });

        //Portrait mode show go to list button
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            showAll = (Button) v.findViewById(R.id.goTOList_button);
            showAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBack.onShowAllPressed();
                }
            });
        }

    }

    /**
     * Adds a given item
     */
    private void addItem()
    {
        if ((whatField.getText().length() > 0) && (whereField.getText().length() > 0)) {
            repository.put(makeThing());
            whatField.setText("");
            whereField.setText("");
            updateUI();

            mCallBack.onItemAdded();
        }
    }

    private void makeToast(String string) {
        Context context = getActivity().getApplicationContext();
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    private void setTextFields(View v) {
        lastAdded = (TextView) v.findViewById(R.id.last_thing);
        whatField = (EditText) v.findViewById(R.id.what_text);
        whereField = (EditText) v.findViewById(R.id.where_text);
        whereField.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
               boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    addItem();
                    handled = true;
                }

                return handled;
            }
        });

    }

    private String SearchThing(String item) {
        String searchItem = item.toLowerCase().trim();
        List<Thing> things = repository.getAll();

        for (Thing t : things) {
            if (t.getWhat().equals(searchItem)) {
                return t.getWhere();
            }
        }

        return null;
    }

    private Thing makeThing() {
        return new Thing(whatField.getText().toString().toLowerCase().trim(),
                whereField.getText().toString().toLowerCase().trim());
    }

    private void updateUI() {
        List<Thing> Things = repository.getAll();
        if (Things.size() > 0) {
            this.lastAdded.setText(Things.get(Things.size() - 1).toString());
        } else {
            this.lastAdded.setText(getString(R.string.item_NotFound_toast));
        }
    }

    public interface TingleFragmentEventListener {
        void onShowAllPressed();

        void onItemAdded();
    }

}


