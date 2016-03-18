package itu.mmad.dttn.tingle.controller.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

import itu.mmad.dttn.tingle.controller.GenericFragmentActivity;
import itu.mmad.dttn.tingle.model.Thing;
import itu.mmad.dttn.tingle.R;
import itu.mmad.dttn.tingle.model.ThingsDatabase;

/**
 * Represents a detailed view of thing
 */
public class ThingFragment extends Fragment{

    private static final String ARG_THING_ID = "thing_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = -1;

    private Thing mThing;
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
        UUID thingId = (UUID) getArguments().getSerializable(ARG_THING_ID);
        ThingsDatabase database = ((GenericFragmentActivity) getActivity()).getDatabase();
        mThing = database.get(thingId);
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
                mThing.setWhat(s.toString());
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
                mThing.setWhere(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDescriptionField = (EditText) v.findViewById(R.id.description_EditBox);
        mDescriptionField.setText(mThing.getDescription()); //todo check for null pointer exception
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mThing.setDescription(s.toString());
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
