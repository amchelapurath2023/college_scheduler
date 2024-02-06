package com.example.myapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClassAssignment;

import java.util.ArrayList;

public class ClassAssignmentAdapter extends RecyclerView.Adapter<ClassAssignmentAdapter.AssignmentViewHolder> {

    private ArrayList<ClassAssignment> assignments;
    private OnDeleteItemClickListener deleteItemClickListener;

    public interface OnDeleteItemClickListener {
        void onDeleteItemClick(int position);
    }

    public ClassAssignmentAdapter(ArrayList<ClassAssignment> assignments, OnDeleteItemClickListener listener) {
        this.assignments = assignments;
        this.deleteItemClickListener = listener;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_assignment, parent, false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ClassAssignment assignment = assignments.get(position);
        holder.taskTextView.setText(assignment.getTask());
        holder.dateTextView.setText(assignment.getDate());
        holder.classTextView.setText(assignment.getAssignedClass());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (deleteItemClickListener != null) {
                    deleteItemClickListener.onDeleteItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public static class AssignmentViewHolder extends RecyclerView.ViewHolder {
        TextView taskTextView, classTextView, dateTextView;
        ImageButton deleteButton;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTextView = itemView.findViewById(R.id.taskTextView);
            classTextView = itemView.findViewById(R.id.classTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            deleteButton = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
