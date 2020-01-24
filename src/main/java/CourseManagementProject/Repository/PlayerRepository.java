package CourseManagementProject.Repository;

import CourseManagementProject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Student, Integer> {

}
