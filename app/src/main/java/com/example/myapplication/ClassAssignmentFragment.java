package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClassAssignment;
import com.example.myapplication.ClassAssignmentAdapter;
import com.example.myapplication.MockDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassAssignmentFragment extends Fragment {

    private Button addButton;
    private EditText taskEditText, classEditText, dateEditText;
    private Spinner sortSpinner;
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
        dateEditText = rootView.findViewById(R.id.dateEditText);
        sortSpinner = rootView.findViewById(R.id.sortSpinner);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAssignment();
            }
        });

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        adapter = new ClassAssignmentAdapter((ArrayList<ClassAssignment>) MockDatabase.DATABASE.getAssignmentList(), new ClassAssignmentAdapter.OnDeleteItemClickListener() {
            @Override
            public void onDeleteItemClick(int position) {
                deleteAssignment(position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        // Sorting options
        Button sortButton = rootView.findViewById(R.id.sortButton);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortAssignments();
            }
        });

        return rootView;
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        dateEditText.setText(selectedDate);
                    }
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void addAssignment() {
        String task = taskEditText.getText().toString();
        String classText = classEditText.getText().toString();
        String dateText = dateEditText.getText().toString();

        if (!task.isEmpty() && !classText.isEmpty()) {
            ClassAssignment assignment = new ClassAssignment(task, classText, dateText);
            MockDatabase.DATABASE.addAssignment(assignment);
            adapter.notifyItemInserted(MockDatabase.DATABASE.getAssignmentList().size() - 1);
            taskEditText.setText("");
            dateEditText.setText("");
            classEditText.setText("");
        }
    }

    private void deleteAssignment(int position) {
        MockDatabase.DATABASE.removeAssignment(MockDatabase.DATABASE.getAssignmentList().get(position));
        adapter.notifyItemRemoved(position);
    }

    // Method to sort assignments based on the selected sorting option
    private void sortAssignments() {
        int selectedItemPosition = sortSpinner.getSelectedItemPosition();
        switch (selectedItemPosition) {
            case 0: // Sort by due date
                MockDatabase.DATABASE.sortAssignmentsByDueDate();;
                break;
            case 1: // Sort by course
                MockDatabase.DATABASE.sortAssignmentsByCourse();
                break;
        }
        adapter.updateList(MockDatabase.DATABASE.getAssignmentList());
    }

}
