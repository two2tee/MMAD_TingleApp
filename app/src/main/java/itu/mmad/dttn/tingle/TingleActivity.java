package itu.mmad.dttn.tingle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import itu.mmad.dttn.tingle.Model.Thing;

public class TingleActivity extends AppCompatActivity {
    // GUI variables
    private Button addThing;
    private TextView lastAdded;
    private TextView newWhat, newWhere;

    //fake database
    private List<Thing> thingsDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);
        thingsDB = new ArrayList<Thing>();
        fillThingsDB();

        //Accessing GUI elements
        lastAdded = (TextView) findViewById(R.id.last_thing);
        updateUI();

        //Button
        addThing = (Button) findViewById(R.id.add_button);
        addThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((newWhat.getText().length()>0) && (newWhere.getText().length()>0))
                {
                    thingsDB.add(
                            new Thing(newWhat.getText().toString(),
                                      newWhere.getText().toString()));
                    newWhat.setText(""); newWhere.setText("");
                    updateUI();
                }
            }
        });

        newWhat = (TextView) findViewById(R.id.what_text);
        newWhere = (TextView) findViewById(R.id.where_text);

    }


    private void updateUI() {
        int size = thingsDB.size();
        if (size>0){
            lastAdded.setText(thingsDB.get(size-1).toString());
        }
    }

    private void fillThingsDB() {
        thingsDB.add(new Thing("Android Pnone", "Desk"));
        thingsDB.add(new Thing("Big Nerd book", "Desk"));
    }
}
