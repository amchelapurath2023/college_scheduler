package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ToDoAdapter;

import java.util.ArrayList;

public class ToDoFragment extends Fragment {

    private Button button;
    private EditText edit_text;
    private ToDoAdapter adapter;
    private RecyclerView recyclerView;

    public ToDoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_todo, container, false);

        recyclerView = rootView.findViewById(R.id.list);
        button = rootView.findViewById(R.id.button);
        edit_text = rootView.findViewById(R.id.edit_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        adapter = new ToDoAdapter((ArrayList<String>) MockDatabase.DATABASE.getToDoList(), new ToDoAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteItem(position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void addItem() {
        String itemText = edit_text.getText().toString();

        if (!itemText.isEmpty()) {
            MockDatabase.DATABASE.addToDoItem(itemText);
            adapter.notifyItemInserted(MockDatabase.DATABASE.getToDoList().size() - 1);
            edit_text.setText("");
        }
    }

    private void deleteItem(int position) {
        MockDatabase.DATABASE.removeToDoItem(MockDatabase.DATABASE.getToDoList().get(position));
        adapter.notifyItemRemoved(position);
    }
}
