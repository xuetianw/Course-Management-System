package Maven.Project.TicTacToe.Service;

import Maven.Project.TicTacToe.model.Player;

import java.util.List;

public interface PlayerService {

    public List<Player> findAll();

    public Player findById(String theId);

    public void save(Player theEmployee);

    public void deleteById(String theId);

}
