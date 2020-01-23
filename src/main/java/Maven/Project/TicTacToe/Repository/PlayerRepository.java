package Maven.Project.TicTacToe.Repository;

import Maven.Project.TicTacToe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<User, Integer> {

}
