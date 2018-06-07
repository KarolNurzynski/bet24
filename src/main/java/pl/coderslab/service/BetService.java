package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Bet;

import java.util.List;

@Service
public interface BetService {

    public List<Bet> findAllBets();

    public Bet findBetById(Long id);

    public Bet saveBet(Bet bet);

    public void saveListOfBets(List<Bet> listOfBets);

    public Bet editBet(Bet bet);

    public void deleteBet(Long id);

    public List<Bet> findAllActiveBetsByUserId(Long id);

    public List<Bet> findAllBetsByUserId(Long id);

    public List<Bet> findAllUnpaidFinishedBets();

    public List<Bet> findAllPaidFinishedBets();

    public List<Bet> findAllBetsByEventId(Long event_id);

}
