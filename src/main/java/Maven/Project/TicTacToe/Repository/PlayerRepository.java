package Maven.Project.TicTacToe.Repository;

import Maven.Project.TicTacToe.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
