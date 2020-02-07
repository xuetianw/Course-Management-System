package io.futurestud.retrofit1.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;

    private String email;

    private String first_name;

    private String last_name;


    private String password;

    private List<Course> courses;

    public Student(int id) {
        this.id = id;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> add_course(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        return courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Student() {
//        times_played = 0;
//    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

//    public int getTimes_played() {
//        return times_played;
//    }
//
//    public void setTimes_played(int times_played) {
//        this.times_played = times_played;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
