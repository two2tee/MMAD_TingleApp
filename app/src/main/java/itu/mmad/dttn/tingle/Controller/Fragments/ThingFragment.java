package itu.mmad.dttn.tingle.Controller.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import itu.mmad.dttn.tingle.Model.Thing;
import itu.mmad.dttn.tingle.R;

/**
 * Represents a detailed view of thing
 */
public class ThingFragment extends Fragment{
    private Thing mThing;
    private EditText mWhatField;
    private EditText mWhereField;
    private EditText mDescriptionField;
    private Button mDateButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thing,parent, false);
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

    private void setButtons(View v){
        mDateButton = (Button) v.findViewById(R.id.thing_date_button);
        mDateButton.setText(mThing.getDate().toString());
        mDateButton.setEnabled(false);

    }
}
