package Maven.Project.TicTacToe.Service;

import Maven.Project.TicTacToe.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(int theId);

    public void save(User user);

    public void deleteById(int theId);

}
