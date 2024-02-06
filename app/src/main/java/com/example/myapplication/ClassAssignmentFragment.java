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

import com.example.myapplication.ClassAssignment;
import com.example.myapplication.ClassAssignmentAdapter;

import java.util.ArrayList;

public class ClassAssignmentFragment extends Fragment {

    private ArrayList<ClassAssignment> assignments;
    private Button addButton;
    private EditText taskEditText, classEditText;
    private ClassAssignmentAdapter adapter;
    private RecyclerView recyclerView;

    public ClassAssignmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_class_assignment, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        addButton = rootView.findViewById(R.id.addButton);
        taskEditText = rootView.findViewById(R.id.taskEditText);
        classEditText = rootView.findViewById(R.id.classEditText);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAssignment();
            }
        });

        assignments = new ArrayList<>();
        adapter = new ClassAssignmentAdapter(assignments, new ClassAssignmentAdapter.OnDeleteItemClickListener() {
            @Override
            public void onDeleteItemClick(int position) {
                deleteAssignment(position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void addAssignment() {
        String task = taskEditText.getText().toString();
        String classText = classEditText.getText().toString();

        if (!task.isEmpty() && !classText.isEmpty()) {
            ClassAssignment assignment = new ClassAssignment(task, classText);
            assignments.add(assignment);
            adapter.notifyItemInserted(assignments.size() - 1);
            taskEditText.setText("");
            classEditText.setText("");
        }
    }

    private void deleteAssignment(int position) {
        assignments.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
