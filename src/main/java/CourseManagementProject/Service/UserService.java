package CourseManagementProject.Service;

import CourseManagementProject.model.Student;

import java.util.List;

public interface UserService {

    public List<Student> findAll();

    public Student findById(int theId);

    public void save(Student student);

    public void deleteById(int theId);

}
