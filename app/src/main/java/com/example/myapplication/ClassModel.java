package com.example.myapplication;

import java.util.List;

public class ClassModel {
    private String className;
    private String dateTime;
    private List<String> recurringDays;

    private String profName;

    public ClassModel(String className, String dateTime, String profName, List<String> recurringDays) {
        this.className = className;
        this.dateTime = dateTime;
        this.profName = profName;
        this.recurringDays = recurringDays;

    }

    public String getClassName() {
        return className;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getProfName() {return  profName;}

    public List<String> getRecurringDays() {
        return recurringDays;
    }
}
