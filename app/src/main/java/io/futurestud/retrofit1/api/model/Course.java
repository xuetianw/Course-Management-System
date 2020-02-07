package io.futurestud.retrofit1.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private int id;

    private String title;

    private Student student;

    public Course(String title) {
        this.title = title;
    }

    public Course(int id) {
        this.id = id;
    }

    public void setStudents(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + "]";
    }
}
