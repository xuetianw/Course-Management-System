package Maven.Project.TicTacToe.Repository;

import Maven.Project.TicTacToe.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Integer> {

}
