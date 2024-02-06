package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ClassFragment extends Fragment {
    private List<ClassModel> classList;
    private ClassAdapter classAdapter;

    private EditText editTextClassName;
    private EditText editTextClassTime;

    private EditText editTextProfName;
    private CheckBox checkBoxMonday, checkBoxTuesday, checkBoxWednesday, checkBoxThursday, checkBoxFriday;
    private Button buttonAddClass;
    private RecyclerView recyclerViewClasses;
    private CalendarView calendarView;

    private Calendar selectedDateTime = Calendar.getInstance();

    public ClassFragment() {

    }

    public static ClassFragment newInstance() {
        return new ClassFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_class, container, false);

        editTextClassName = rootView.findViewById(R.id.editTextClassName);
        editTextClassTime = rootView.findViewById(R.id.editTextClassTime);
        editTextProfName = rootView.findViewById(R.id.editTextProfName);
        checkBoxMonday = rootView.findViewById(R.id.checkBoxMonday);
        checkBoxTuesday = rootView.findViewById(R.id.checkBoxTuesday);
        checkBoxWednesday = rootView.findViewById(R.id.checkBoxWednesday);
        checkBoxThursday = rootView.findViewById(R.id.checkBoxThursday);
        checkBoxFriday = rootView.findViewById(R.id.checkBoxFriday);
        buttonAddClass = rootView.findViewById(R.id.buttonAddClass);
        recyclerViewClasses = rootView.findViewById(R.id.recyclerViewClasses);
        calendarView = rootView.findViewById(R.id.calendarView);

        classList = new ArrayList<>();
        classAdapter = new ClassAdapter(classList);

        recyclerViewClasses.setLayoutManager(new LinearLayoutManager(requireContext()));
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

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDateTime.set(year, month, dayOfMonth, selectedDateTime.get(Calendar.HOUR_OF_DAY), selectedDateTime.get(Calendar.MINUTE));
                updateRecyclerView();
            }
        });
        return rootView;
    }

private void addClass() {
        String className = editTextClassName.getText().toString().trim();
        String profName = editTextProfName.getText().toString().trim();
        List<String> recurringDays = getSelectedRecurringDays();

        if (!className.isEmpty() && !recurringDays.isEmpty()) {
            ClassModel newClass = new ClassModel(className, formatDateAndTime(selectedDateTime), profName, recurringDays);
            classList.add(newClass);
            classAdapter.notifyDataSetChanged();
            editTextClassName.getText().clear();
            editTextProfName.getText().clear();
            clearRecurringDays();
        } else {
            Toast.makeText(requireContext(), "Class name and recurring days cannot be empty", Toast.LENGTH_SHORT).show();
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
                requireContext(),

                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDateTime.set(Calendar.MINUTE, minute);
                        editTextClassTime.setText(formatTime(selectedDateTime));
                    }
                },
                selectedDateTime.get(Calendar.HOUR_OF_DAY),
                selectedDateTime.get(Calendar.MINUTE),
                false
        );

        timePickerDialog.show();
    }

    private void updateRecyclerView() {
        List<ClassModel> filteredClasses = filterClassesByDate(classList, formatDate(selectedDateTime));
        classAdapter.setClassList(filteredClasses);
        classAdapter.notifyDataSetChanged();
    }

    private List<ClassModel> filterClassesByDate(List<ClassModel> allClasses, String selectedDate) {
        List<ClassModel> filteredClasses = new ArrayList<>();

        for (ClassModel classModel : allClasses) {
            if (classModel.getRecurringDays().contains(getDayName(selectedDateTime.get(Calendar.DAY_OF_WEEK)))) {
                filteredClasses.add(classModel);
            }
        }

        return filteredClasses;
    }

    private String formatTime(Calendar calendar) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return timeFormat.format(calendar.getTime());
    }

    private String formatDate(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    private String getDayName(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            default:
                return "";
        }
    }
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassModel currentClass = classList.get(position);
        holder.textViewClassName.setText(currentClass.getClassName());
        holder.textViewDateTime.setText(currentClass.getDateTime());
        holder.textViewRecurringDays.setText(currentClass.getRecurringDays().toString());

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
        classList.remove(position);
        classAdapter.notifyDataSetChanged();
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder {

        TextView textViewClassName;
        TextView textViewDateTime;

        TextView textViewProfName;
        TextView textViewRecurringDays;
        ImageButton buttonDelete;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewClassName = itemView.findViewById(R.id.textViewClassName);
            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
            textViewProfName = itemView.findViewById(R.id.textViewProfName);
            textViewRecurringDays = itemView.findViewById(R.id.textViewRecurringDays);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
