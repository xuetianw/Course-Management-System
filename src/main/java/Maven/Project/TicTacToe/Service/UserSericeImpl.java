package Maven.Project.TicTacToe.Service;

import Maven.Project.TicTacToe.Repository.PlayerRepository;
import Maven.Project.TicTacToe.model.User;
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
    public List<User> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = playerRepository.findById(theId);

        if (result.isPresent()) {
            return result.get();
        }

        return null;

    }

    @Override
    public void save(User user) {
        playerRepository.save(user);
    }

    @Override
    public void deleteById(int theId) {
        playerRepository.deleteById(theId);
    }
}
