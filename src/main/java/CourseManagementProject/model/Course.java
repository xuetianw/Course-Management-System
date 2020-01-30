package CourseManagementProject.model;

import CourseManagementProject.others.StudentListDeserializer;
import CourseManagementProject.others.StudentListSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;


    @ManyToMany(fetch=FetchType.EAGER,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="course_student",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id")
    )
    @JsonSerialize(using = StudentListSerializer.class)
    @JsonDeserialize(using = StudentListDeserializer.class)
    private List<Student> students;

    public Course(String title) {
        this.title = title;
    }

    public Course() {
    }

    public Course(int id) {
        this.id = id;
    }

    public Course(int id, String title, List<Student> students) {
        this.id = id;
        this.title = title;
        this.students = students;
    }

    public Course(int id, String title) {
        this.title = title;
        this.id = id;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // add a convenience method

    public void addStudent(Student student) {

        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(student);
    }
}
