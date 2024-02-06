package com.example.myapplication;

public class MockDatabase {
    public static ScheduleManager DATABASE;

    public static void start(){
        DATABASE = new ScheduleManager();
    }
}


