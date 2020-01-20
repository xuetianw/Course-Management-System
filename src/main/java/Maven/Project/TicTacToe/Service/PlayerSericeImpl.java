package Maven.Project.TicTacToe.Service;

import Maven.Project.TicTacToe.Repository.PlayerRepository;
import Maven.Project.TicTacToe.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerSericeImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerSericeImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(String email) {
        Optional<Player> result = playerRepository.findById(email);

        if (result.isPresent()) {
            return result.get();
        }

        return null;

    }

    @Override
    public void save(Player thePlayer) {
        playerRepository.save(thePlayer);
    }

    @Override
    public void deleteById(String theId) {
        playerRepository.deleteById(theId);
    }
}
