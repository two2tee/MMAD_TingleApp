package itu.mmad.dttn.tingle.controller.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;

import itu.mmad.dttn.tingle.R;
import itu.mmad.dttn.tingle.controller.BaseActivity;
import itu.mmad.dttn.tingle.model.TempThingToStore;
import itu.mmad.dttn.tingle.model.Thing;
import itu.mmad.dttn.tingle.model.database.ThingsDatabase;

/**
 * Represents a detailed view of thing
 */
public class ThingFragment extends Fragment{

    private static final String ARG_THING_ID = "thing_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = -1;

    private Thing mThing;
    private TempThingToStore mTempThing;
    private EditText mWhatField;
    private EditText mWhereField;
    private EditText mDescriptionField;
    private Button mDateButton;


    public static ThingFragment newInstance(UUID thingId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_THING_ID, thingId);
        ThingFragment fragment = new ThingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID thingId = (UUID) getArguments().getSerializable(ARG_THING_ID);
        ThingsDatabase database = ((BaseActivity) getActivity()).getDatabase();
        mThing = database.get(thingId);
        mTempThing = new TempThingToStore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thing, parent, false);
        setTextFields(v);
        setButtons(v);
        return v;

    }

    private void setTextFields(View v){
        mWhatField = (EditText) v.findViewById(R.id.what_text);
        mWhatField.setText(mThing.getWhat());
        mWhatField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTempThing.setWhat(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mWhereField = (EditText) v.findViewById(R.id.where_text);
        mWhereField.setText(mThing.getWhere());
        mWhereField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTempThing.setWhere(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDescriptionField = (EditText) v.findViewById(R.id.description_EditBox);
        mDescriptionField.setText(mThing.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTempThing.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != Activity.RESULT_OK) {
            return;
        }
        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mThing.setDate(date);
            updateDate();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.back_button:
                goBack();
                return true;
            case R.id.save_button:
                saveChanges();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveChanges() {
        if(mTempThing.isHasChanged()) {

            //Override items
            if (mTempThing.getWhat() != null)
                mThing.setWhat(mTempThing.getWhat());

            else if (mTempThing.getWhere() != null)
                mThing.setWhere(mTempThing.getWhere());

            else if (mTempThing.getDate() != null)
                mThing.setDate(mTempThing.getDate());

            else if (mTempThing.getDescription() != null)
                mThing.setDescription(mTempThing.getDescription());

            //save changes
            boolean isSaved = ((BaseActivity) getActivity()).getDatabase().update(mThing);

            //Validate
            if(isSaved){
                makeToast(R.string.saved);
            }
            else
                makeToast(R.string.something_Went_Wrong);
        }
        else
            makeToast(R.string.no_changes);


    }

    private void makeToast(int s){
        Toast.makeText(getActivity().getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

    private void goBack() {
        getActivity().finish();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_thing,menu);
    }

    private void setButtons(View v){
        mDateButton = (Button) v.findViewById(R.id.thing_date_button);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mThing.getDate());
                dialog.setTargetFragment(ThingFragment.this,REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
            }
        });
    }

    private void updateDate() {
        mDateButton.setText(mThing.getDate().toString());
    }
}
