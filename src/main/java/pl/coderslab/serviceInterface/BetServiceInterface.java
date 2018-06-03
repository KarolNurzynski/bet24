package pl.coderslab.serviceInterface;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Bet;

import java.util.List;

@Service
public interface BetServiceInterface {

    public List<Bet> findAllBets();

    public Bet findBetById(Long id);

    public Bet saveBet(Bet bet);

    public Bet editBet(Bet bet);

    public void deleteBet(Long id);

}
