package io.futurestud.retrofit1.api.model;

public class Player {
    private String email;

    private String first_name;

    private String last_name;


    private int times_played;

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

    public int getTimes_played() {
        return times_played;
    }

    public void setTimes_played(int times_played) {
        this.times_played = times_played;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
