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

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    private List<ClassModel> classList;

    public ClassAdapter(List<ClassModel> classList) {
        this.classList = classList;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassModel currentClass = classList.get(position);
        holder.textViewClassName.setText(currentClass.getClassName());
        holder.textViewDateTime.setText(currentClass.getDateTime());
        holder.textViewRecurringDays.setText(currentClass.getRecurringDays().toString());
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    // New method to update the classList
    public void setClassList(List<ClassModel> newList) {
        classList = newList;
        notifyDataSetChanged();
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder {

        TextView textViewClassName;
        TextView textViewDateTime;
        TextView textViewRecurringDays;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewClassName = itemView.findViewById(R.id.textViewClassName);
            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
            textViewRecurringDays = itemView.findViewById(R.id.textViewRecurringDays);
        }
    }
}
