package CourseManagementProject.Service;

import CourseManagementProject.Repository.PlayerRepository;
import CourseManagementProject.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSericeImpl implements UserService {

    private PlayerRepository playerRepository;

    @Autowired
    public UserSericeImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Student> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Student findById(int theId) {
        Optional<Student> result = playerRepository.findById(theId);

        if (result.isPresent()) {
            return result.get();
        }

        return null;

    }

    @Override
    public void save(Student student) {
        playerRepository.save(student);
    }

    @Override
    public void deleteById(int theId) {
        playerRepository.deleteById(theId);
    }
}
