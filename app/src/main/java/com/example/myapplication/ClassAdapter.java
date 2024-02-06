package com.example.myapplication;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClassModel;

import java.util.List;

// ClassAdapter.java
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
    public void onBindViewHolder(@NonNull ClassViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ClassModel currentClass = classList.get(position);
        holder.textViewClassName.setText(currentClass.getClassName());
        holder.textViewDateTime.setText(currentClass.getDateTime());
        holder.textViewProfName.setText(currentClass.getProfName());
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

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public void setClassList(List<ClassModel> newList) {
        classList = newList;
        notifyDataSetChanged();
    }

    private void deleteItem(int position) {
        classList.remove(position);
        notifyDataSetChanged();
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder {

        TextView textViewClassName;
        TextView textViewDateTime;
        TextView textViewRecurringDays;

        TextView textViewProfName;
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
