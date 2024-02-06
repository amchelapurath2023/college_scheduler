package com.example.myapplication;
public class ClassAssignment {
    private String task;
    private String assignedClass;
    private String date;

    public ClassAssignment(String task, String assignedClass, String date) {
        this.task = task;
        this.assignedClass = assignedClass;
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public String getAssignedClass() {
        return assignedClass;
    }

    public String getDate(){return date;}
}
