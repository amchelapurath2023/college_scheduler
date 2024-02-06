package com.example.myapplication;

public class ExamDetails {
    private String date;
    private String time;
    private String location;

    private String examClass;

    public ExamDetails(String date, String time, String location, String examClass) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.examClass = examClass;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getExamClass(){
        return examClass;
    }

    @Override
    public String toString() {
        // This method is important for displaying the item in the ListView
        return "Date: " + date + "\nTime: " + time + "\nLocation: " + location + "\nClass: " + examClass;
    }
}

