package io.futurestud.retrofit1.api.model;

import java.util.List;

public class Course {
    private int id;

    private String title;

//    private List<Player> users;

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

//    public List<Player> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<Player> users) {
//        this.users = users;
//    }
}
