package itu.mmad.dttn.tingle.Controller.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Iterator;

import itu.mmad.dttn.tingle.Model.InMemoryRepository;
import itu.mmad.dttn.tingle.Model.Interfaces.IRepository;
import itu.mmad.dttn.tingle.Model.Thing;
import itu.mmad.dttn.tingle.R;

/**
 * Fragment for the list page
 */
public class ListFragment extends Fragment {

    OnBackPressedListener mCallBack;

    public interface OnBackPressedListener {
        public void onBackPressed();
    }


    //Database
    private IRepository<Thing> repository;

    //GUI
    private View view;
    private Button back;
    private Button delete;
    private ListView itemList;

    private int selectedItemPos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repository = InMemoryRepository.getInMemoryRepository();
        selectedItemPos = -1;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);

        setButtons();
        setItemList();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //Checks if parent activity has implemented the
        //callback interface
        try {
            mCallBack = (OnBackPressedListener) context;
            repository = InMemoryRepository.getInMemoryRepository();

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnBackPressedListener");
        }
    }

    private void makeToast(String string) {
        Context context = getActivity().getApplicationContext();
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    private void setButtons() {

        back = (Button) view.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onBackPressed();
            }
        });


        delete = (Button) view.findViewById(R.id.delete_Button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItemPos == -1) {
                    makeToast(getString(R.string.no_item_selected));
                } else {
                    repository.delete(selectedItemPos);
                    setItemList();
                    selectedItemPos = -1;
                }
            }
        });

    }


    private void setItemList() {
        Thing[] things = new Thing[repository.returnSize() + 1];
        Iterator<Thing> items = repository.getAll();
        int count = 0;
        while (items.hasNext()) {
            things[count] = items.next();
            count++;
        }

        final ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.list_view_row_item, R.id.list_item_text, things);
        itemList = (ListView) view.findViewById(R.id.item_list);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemPos = position;
                Thing selectedItem = (Thing) adapter.getItem(position);
                makeToast(getString(R.string.item_selected) + " " + selectedItem.getWhat());
            }
        });
    }

}