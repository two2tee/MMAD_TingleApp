package itu.mmad.dttn.tingle.controller.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.os.OperationCanceledException;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import itu.mmad.dttn.tingle.R;
import itu.mmad.dttn.tingle.controller.DetailedThingActivity;
import itu.mmad.dttn.tingle.controller.GenericFragmentActivity;
import itu.mmad.dttn.tingle.model.Thing;
import itu.mmad.dttn.tingle.model.ThingsDatabase;

/**
 * Fragment for the list page
 */
public class ListFragment extends Fragment {

    ListFragmentEventListener mCallBack;
    //Database
    private ThingsDatabase repository;
    //GUI
    private View view;
    private RecyclerView itemList;
    private ThingAdapter mAdapter;
    private List<Thing> selectedItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        repository = ((GenericFragmentActivity) getActivity()).getDatabase();
        selectedItems = new ArrayList<>();

        setItemList();
        updateList();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList()
    {
        List<Thing> things = repository.getAll();
        if(mAdapter == null){
            mAdapter = new ThingAdapter(things);
            itemList.setAdapter(mAdapter);
        } else
            mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //Checks if parent activity has implemented the
        //callback interface
        try {
            mCallBack = (ListFragmentEventListener) context;
            repository = ((GenericFragmentActivity) getActivity()).getDatabase();

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ListFragmentEventListener");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_Button:
                deleteItem();
                return true;
            case R.id.back_button:
                goBack();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void makeToast(String string) {
        Context context = getActivity().getApplicationContext();
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    private void goBack(){
        //Portrait mode show go to list button
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    mCallBack.onBackPressed();
        }
    }

    private void deleteItem(){
        if (selectedItems.isEmpty()) {
            makeToast(getString(R.string.no_item_selected));
        }
        else
        {

            new AlertDialog.Builder(getActivity())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.warning)
                    .setMessage(R.string.delete_question)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            try
                            {
                                for (Thing thing : selectedItems)
                                {
                                    repository.delete(thing.getId());
                                    mAdapter.mThings.remove(thing);
                                }
                                selectedItems.clear();
                                updateList();

                            } catch (OperationCanceledException e)
                            {
                                makeToast(getString(R.string.something_Went_Wrong));
                            }

                        }

                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        }
    }

    private void setItemList() {
        itemList = (RecyclerView) view.findViewById(R.id.item_list);
        itemList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public interface ListFragmentEventListener {
        void onBackPressed();
    }


    //ViewHolder for list

    private class ThingHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView mThingLabel;
        private CheckBox mSelectedCheckBox;
        private Thing mThing;

        public ThingHolder(View itemView)
        {
            super(itemView);
            mThingLabel = (TextView) itemView.findViewById(R.id.list_item_text);
            mSelectedCheckBox = (CheckBox) itemView.findViewById(R.id.list_checkbox);
            setCheckboxes();
            itemView.setOnClickListener(this);


        }

        public void BindThing(Thing thing){
            mThingLabel.setText(thing.toString());
            mSelectedCheckBox.setChecked(false);
            mThing = thing;
        }

        private void setCheckboxes(){
            mSelectedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if(isChecked)
                    {
                        selectedItems.add(mThing);
                    }
                    else
                    {
                        selectedItems.remove(mThing);
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            Intent intent = DetailedThingActivity.newIntent(getActivity(),mThing.getId());
            startActivity(intent);

        }


    }

      //Adapter for list
    private class ThingAdapter extends RecyclerView.Adapter<ThingHolder>{
        private List<Thing> mThings;

        public ThingAdapter(List<Thing> things)
        {
            mThings = things;
        }

        @Override
        public ThingHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_view_row_item,parent,false);
            return new ThingHolder(view);
        }

        @Override
        public void onBindViewHolder(ThingHolder holder, int position)
        {
            Thing thing = mThings.get(position);
            holder.BindThing(thing);
        }

        @Override
        public int getItemCount()
        {
            return mThings.size();
        }
    }


}
