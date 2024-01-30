// ClassModel.java
package com.example.myapplication;

public class ClassModel {
    private String className;
    private String classTime;
    private String selectedDate;

    public ClassModel(String className, String classTime, String selectedDate) {
        this.className = className;
        this.classTime = classTime;
        this.selectedDate = selectedDate;
    }

    public String getClassName() {
        return className;
    }

    public String getClassTime() {
        return classTime;
    }

    public String getSelectedDate() {
        return selectedDate;
    }
}
