package io.futurestud.retrofit1.api.model;

import java.util.List;

public class Course {
    private int id;

    private String title;


//    private List<Student> users;
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

    public Course(String title) {
        this.title = title;
    }

//    public List<Student> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<Student> users) {
//        this.users = users;
//    }
}
