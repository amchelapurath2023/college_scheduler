// MainActivity.java
package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClassAdapter;
import com.example.myapplication.ClassModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private List<ClassModel> classList;
    private ClassAdapter classAdapter;

    private EditText editTextClassName;
    private EditText editTextClassTime;
    private CalendarView calendarView;
    private Button buttonAddClass;
    private RecyclerView recyclerViewClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextClassName = findViewById(R.id.editTextClassName);
        editTextClassTime = findViewById(R.id.editTextClassTime);
        calendarView = findViewById(R.id.calendarView);
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
    }

    private void addClass() {
        String className = editTextClassName.getText().toString().trim();
        String classTime = editTextClassTime.getText().toString().trim();

        long selectedDateMillis = calendarView.getDate();
        String selectedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(selectedDateMillis));

        if (!className.isEmpty() && !classTime.isEmpty()) {
            ClassModel newClass = new ClassModel(className, classTime, selectedDate);
            classList.add(newClass);
            classAdapter.notifyDataSetChanged();
            editTextClassName.getText().clear();
            editTextClassTime.getText().clear();
        } else {
            Toast.makeText(this, "Class name and time cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}
