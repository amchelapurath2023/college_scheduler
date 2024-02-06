package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ExamAdapter;
import com.example.myapplication.ExamDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ExamFragment extends Fragment {

    private ArrayList<ExamDetails> examList;
    private RecyclerView examRecyclerView;
    private Button addButton, chooseDateButton;
    private EditText dateEditText, timeEditText, locationEditText, examClass;

    private Calendar selectedDateTime = Calendar.getInstance();

    private ExamAdapter examAdapter;

    public ExamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_exam, container, false);

        examRecyclerView = rootView.findViewById(R.id.examRecyclerView);
        addButton = rootView.findViewById(R.id.addButton);
        dateEditText = rootView.findViewById(R.id.dateEditText);
        timeEditText = rootView.findViewById(R.id.timeEditText);
        locationEditText = rootView.findViewById(R.id.locationEditText);
        examClass = rootView.findViewById(R.id.examClass);

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        examRecyclerView.setLayoutManager(layoutManager);

        examList = new ArrayList<>();
        examAdapter = new ExamAdapter(requireContext(), examList);
        examRecyclerView.setAdapter(examAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addExam();
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

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),

                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDateTime.set(Calendar.MINUTE, minute);
                        timeEditText.setText(formatTime(selectedDateTime));
                    }
                },
                selectedDateTime.get(Calendar.HOUR_OF_DAY),
                selectedDateTime.get(Calendar.MINUTE),
                false
        );

        timePickerDialog.show();
    }

    private String formatTime(Calendar calendar) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return timeFormat.format(calendar.getTime());
    }

    private void addExam() {
        String date = dateEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String location = locationEditText.getText().toString();
        String subject = examClass.getText().toString();

        if (!date.isEmpty() && !time.isEmpty() && !location.isEmpty() && !subject.isEmpty()) {
            ExamDetails exam = new ExamDetails(date, time, location, subject);
            examAdapter.addExam(exam);

            dateEditText.setText("");
            timeEditText.setText("");
            locationEditText.setText("");
            examClass.setText("");
        } else {
            Toast.makeText(requireContext(), "Please enter all details", Toast.LENGTH_SHORT).show();
        }
    }
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        ExamDetails currentExam = examList.get(position);
        holder.dateTextView.setText("Date: " + currentExam.getDate());
        holder.timeTextView.setText("Time: " + currentExam.getTime());
        holder.locationTextView.setText("Location: " + currentExam.getLocation());
        holder.examClassTextView.setText("Class: " + currentExam.getExamClass());

        // Set up a click listener for the delete button
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to handle the delete action
                deleteItem(position);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void deleteItem(int position) {
        examList.remove(position);
        examAdapter.notifyDataSetChanged();
    }

    public static class ExamViewHolder extends RecyclerView.ViewHolder {

        TextView dateTextView;
        TextView timeTextView;
        TextView locationTextView;
        TextView examClassTextView;

        ImageButton buttonDelete;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            examClassTextView = itemView.findViewById(R.id.examClassTextView);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
