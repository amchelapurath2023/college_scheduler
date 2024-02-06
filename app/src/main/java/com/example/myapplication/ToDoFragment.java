package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ToDoFragment extends Fragment {

    private ArrayList<String> items;
    private ListView list;
    private Button button;

    private EditText edit_text;

    private ArrayAdapter<String> itemsAdapter;

    public ToDoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_todo, container, false);

        list = rootView.findViewById(R.id.list);
        button = rootView.findViewById(R.id.button);
        edit_text = rootView.findViewById(R.id.edit_text);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, items);
        list.setAdapter(itemsAdapter);
        setUpListViewListener();

        return rootView;
    }

    private void setUpListViewListener() {
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = requireContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();

                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void addItem(View view) {
        EditText input = edit_text;
        String itemText = input.getText().toString();

        if (!itemText.isEmpty()) {
            itemsAdapter.add(itemText);
            edit_text.setText("");

        }
    }
}
