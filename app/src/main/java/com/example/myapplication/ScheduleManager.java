package com.example.myapplication;
import java.util.ArrayList;
import java.util.List;

public class ScheduleManager {
    private List<ExamDetails> examList;
    private List<String> toDoList;
    private List<ClassModel> classList;
    private List<ClassAssignment> assignmentList;

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
}

