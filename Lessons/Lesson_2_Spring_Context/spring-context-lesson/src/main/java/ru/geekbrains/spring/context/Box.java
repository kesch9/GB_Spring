package ru.geekbrains.spring.context;

import org.springframework.stereotype.Component;

public class Box {
    private StudentService studentService;
    private String color;
    private int size;

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int doSomethingWithStudentService() {
        return studentService.calculateAverageScore();
    }
}
