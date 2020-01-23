package Maven.Project.TicTacToe.Service;

import Maven.Project.TicTacToe.Repository.CourseRepository;
import Maven.Project.TicTacToe.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(int theId) {
        Optional<Course> result = courseRepository.findById(theId);

        if (result.isPresent()) {
            return result.get();
        }

        return null;

    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteById(int theId) {
        courseRepository.deleteById(theId);
    }
}
