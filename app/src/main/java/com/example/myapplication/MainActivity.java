// MainActivity.java
package com.example.myapplication;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClassAdapter;
import com.example.myapplication.ClassModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private List<ClassModel> classList;
    private ClassAdapter classAdapter;

    private EditText editTextClassName;
    private EditText editTextClassTime;
    private CheckBox checkBoxMonday, checkBoxTuesday, checkBoxWednesday, checkBoxThursday, checkBoxFriday;
    private Button buttonAddClass;
    private RecyclerView recyclerViewClasses;

    private Calendar selectedDateTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextClassName = findViewById(R.id.editTextClassName);
        editTextClassTime = findViewById(R.id.editTextClassTime);
        checkBoxMonday = findViewById(R.id.checkBoxMonday);
        checkBoxTuesday = findViewById(R.id.checkBoxTuesday);
        checkBoxWednesday = findViewById(R.id.checkBoxWednesday);
        checkBoxThursday = findViewById(R.id.checkBoxThursday);
        checkBoxFriday = findViewById(R.id.checkBoxFriday);
        buttonAddClass = findViewById(R.id.buttonAddClass);
        recyclerViewClasses = findViewById(R.id.recyclerViewClasses);

        classList = new ArrayList<>();
        classAdapter = new ClassAdapter(classList);

        recyclerViewClasses.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewClasses.setAdapter(classAdapter);

        buttonAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass();
            }
        });

        editTextClassTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

    }

    private void addClass() {
        String className = editTextClassName.getText().toString().trim();
        List<String> recurringDays = getSelectedRecurringDays();

        if (!className.isEmpty() && !recurringDays.isEmpty()) {
            ClassModel newClass = new ClassModel(className, formatDateAndTime(selectedDateTime), recurringDays);
            classList.add(newClass);
            classAdapter.notifyDataSetChanged();
            editTextClassName.getText().clear();
            clearRecurringDays();
        } else {
            Toast.makeText(this, "Class name and recurring days cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    private List<String> getSelectedRecurringDays() {
        List<String> recurringDays = new ArrayList<>();

        if (checkBoxMonday.isChecked()) {
            recurringDays.add("Monday");
        }
        if (checkBoxTuesday.isChecked()) {
            recurringDays.add("Tuesday");
        }
        if (checkBoxWednesday.isChecked()) {
            recurringDays.add("Wednesday");
        }
        if (checkBoxThursday.isChecked()) {
            recurringDays.add("Thursday");
        }
        if (checkBoxFriday.isChecked()) {
            recurringDays.add("Friday");
        }

        return recurringDays;
    }

    private void clearRecurringDays() {
        checkBoxMonday.setChecked(false);
        checkBoxTuesday.setChecked(false);
        checkBoxWednesday.setChecked(false);
        checkBoxThursday.setChecked(false);
        checkBoxFriday.setChecked(false);
    }

    private String formatDateAndTime(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Set the selected time to the Calendar instance
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDateTime.set(Calendar.MINUTE, minute);

                        // Format and display the selected time in the EditText
                        editTextClassTime.setText(formatTime(selectedDateTime));
                    }
                },
                selectedDateTime.get(Calendar.HOUR_OF_DAY),
                selectedDateTime.get(Calendar.MINUTE),
                false  // Set to true for 24-hour format, false for 12-hour format
        );

        timePickerDialog.show();
    }

    // Helper method to format time
    private String formatTime(Calendar calendar) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return timeFormat.format(calendar.getTime());
    }

}
