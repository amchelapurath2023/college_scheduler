package com.example.myapplication;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScheduleManager {
    private List<ExamDetails> examList;
    private List<String> toDoList;
    private List<ClassModel> classList;
    private static List<ClassAssignment> assignmentList;

    public ScheduleManager() {
        examList = new ArrayList<>();
        toDoList = new ArrayList<>();
        classList = new ArrayList<>();
        assignmentList = new ArrayList<>();
    }

    // Getters
    public List<ExamDetails> getExamList() {
        return examList;
    }

    public List<String> getToDoList() {
        return toDoList;
    }

    public List<ClassModel> getClassList() {
        return classList;
    }

    public List<ClassAssignment> getAssignmentList() {
        return assignmentList;
    }

    // Setters
    public void setExamList(List<ExamDetails> examList) {
        this.examList = examList;
    }

    public void setToDoList(List<String> toDoList) {
        this.toDoList = toDoList;
    }

    public void setClassList(List<ClassModel> classList) {
        this.classList = classList;
    }

    public void setAssignmentList(List<ClassAssignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    // Update methods
    public void updateExamList(List<ExamDetails> updatedExamList) {
        examList.clear();
        examList.addAll(updatedExamList);
    }

    public void updateToDoList(List<String> updatedToDoList) {
        toDoList.clear();
        toDoList.addAll(updatedToDoList);
    }

    public void updateClassList(List<ClassModel> updatedClassList) {
        classList.clear();
        classList.addAll(updatedClassList);
    }

    public void updateAssignmentList(List<ClassAssignment> updatedAssignmentList) {
        assignmentList.clear();
        assignmentList.addAll(updatedAssignmentList);
    }

    // Add methods
    public void addExam(ExamDetails exam) {
        examList.add(exam);
    }

    public void addToDoItem(String toDoItem) {
        toDoList.add(toDoItem);
    }

    public void addClass(ClassModel className) {
        classList.add(className);
    }

    public void addAssignment(ClassAssignment assignment) {
        assignmentList.add(assignment);
    }

    // Remove methods
    public void removeExam(ExamDetails exam) {
        examList.remove(exam);
    }

    public void removeToDoItem(String toDoItem) {
        toDoList.remove(toDoItem);
    }

    public void removeClass(ClassModel className) {
        classList.remove(className);
    }

    public void removeAssignment(ClassAssignment assignment) {
        assignmentList.remove(assignment);
    }

    public static void sortAssignmentsByDueDate() {
        List<ClassAssignment> sortedList = assignmentList;
        Collections.sort(sortedList, new Comparator<ClassAssignment>() {
            @Override
            public int compare(ClassAssignment assignment1, ClassAssignment assignment2) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = null;
                try {
                    date1 = dateFormat.parse(assignment1.getDate());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Date date2 = null;
                try {
                    date2 = dateFormat.parse(assignment2.getDate());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                return date1.after(date2) ? 1 : -1;
            }
        });
        assignmentList = sortedList;
        Log.d("Test", assignmentList.toString());
    }

    public static void sortAssignmentsByCourse() {
        List<ClassAssignment> sortedList = new ArrayList<>(assignmentList);
        Collections.sort(sortedList, new Comparator<ClassAssignment>() {
            @Override
            public int compare(ClassAssignment assignment1, ClassAssignment assignment2) {
                return assignment1.getAssignedClass().compareTo(assignment2.getAssignedClass());
            }
        });
        assignmentList.clear();
        assignmentList.addAll(sortedList);
    }
}

