// ClassAdapter.java
package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClassModel;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private List<ClassModel> classes;

    public ClassAdapter(List<ClassModel> classes) {
        this.classes = classes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassModel classModel = classes.get(position);
        holder.textViewClassName.setText(classModel.getClassName());
        holder.textViewClassTime.setText(classModel.getClassTime());
        holder.textViewSelectedDate.setText(classModel.getSelectedDate());
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewClassName;
        TextView textViewClassTime;
        TextView textViewSelectedDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewClassName = itemView.findViewById(R.id.textViewClassName);
            textViewClassTime = itemView.findViewById(R.id.textViewClassTime);
            textViewSelectedDate = itemView.findViewById(R.id.textViewSelectedDate);
        }
    }
}
