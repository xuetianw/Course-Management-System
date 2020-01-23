package Maven.Project.TicTacToe.Service;

import Maven.Project.TicTacToe.model.Player;

import java.util.List;

public interface PlayerService {

    public List<Player> findAll();

    public Player findById(int theId);

    public void save(Player player);

    public void deleteById(int theId);

}
