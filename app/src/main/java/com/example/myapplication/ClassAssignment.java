package com.example.myapplication;
public class ClassAssignment {
    private String task;
    private String assignedClass;

    public ClassAssignment(String task, String assignedClass) {
        this.task = task;
        this.assignedClass = assignedClass;
    }

    public String getTask() {
        return task;
    }

    public String getAssignedClass() {
        return assignedClass;
    }
}
