package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ExamDetails;

import java.util.ArrayList;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {

    private Context context;
    private ArrayList<ExamDetails> examList;

    public ExamAdapter(Context context, ArrayList<ExamDetails> examList) {
        this.context = context;
        this.examList = examList;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exam_list_item, parent, false);
        return new ExamViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ExamDetails currentExam = examList.get(position);
        holder.dateTextView.setText("Date: " + currentExam.getDate());
        holder.timeTextView.setText("Time: " + currentExam.getTime());
        holder.locationTextView.setText("Location: " + currentExam.getLocation());
        holder.examClass.setText("Class: " + currentExam.getExamClass());

        // Set up a click listener for the delete button
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to handle the delete action
                removeExam(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    private void removeExam(int position) {
        examList.remove(position);
        notifyItemRemoved(position);
    }

    public void addExam(ExamDetails exam) {
        //examList.add(exam);
        notifyItemInserted(examList.size() - 1);
    }



    public class ExamViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTextView, timeTextView, locationTextView, examClass;
        ImageButton buttonDelete;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            examClass = itemView.findViewById(R.id.examClassTextView);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }

        public void bind(ExamDetails exam) {
            dateTextView.setText("Date: " + exam.getDate() + "\n");
            timeTextView.setText("Time: " + exam.getTime() + "\n");
            locationTextView.setText("Location: " + exam.getLocation() + "\n");
            examClass.setText("Class: " + exam.getExamClass());
        }
    }
}

