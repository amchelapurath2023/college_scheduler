package com.example.myapplication;

import java.util.List;

public class ClassModel {
    private String className;
    private String dateTime;
    private List<String> recurringDays;

    public ClassModel(String className, String dateTime, List<String> recurringDays) {
        this.className = className;
        this.dateTime = dateTime;
        this.recurringDays = recurringDays;
    }

    public String getClassName() {
        return className;
    }

    public String getDateTime() {
        return dateTime;
    }

    public List<String> getRecurringDays() {
        return recurringDays;
    }
}
