package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Bet;

import java.util.List;

@Service
public interface BetService {

    public List<Bet> findAllBets();

    public Bet findBetById(Long id);

    public Bet saveBet(Bet bet);

    public Bet editBet(Bet bet);

    public void deleteBet(Long id);

}
