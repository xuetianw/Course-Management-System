package Maven.Project.TicTacToe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class Player {

    @Id
    @Column(name="email")
    private String email;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;


    @Column(name="played_time")
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
