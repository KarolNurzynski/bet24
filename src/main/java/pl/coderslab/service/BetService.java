package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Bet;
import pl.coderslab.repository.BetRepository;
import pl.coderslab.serviceInterface.BetServiceInterface;

import java.util.List;

@Service
public class BetService implements BetServiceInterface {
    
    BetRepository betRepository;

    @Autowired
    BetService(BetRepository betRepository) {
        this.betRepository=betRepository;
    }

    public List<Bet> findAllBets() {
        return betRepository.findAll();
    }

    public Bet findBetById(Long id) {
        return betRepository.findById(id).orElseGet(null);
    }

    public Bet saveBet(Bet bet) {
        return betRepository.save(bet);
    }

    public Bet editBet(Bet bet) {
        return betRepository.save(bet);
    }

    public void deleteBet(Long id) {
        betRepository.deleteById(id);
    }

}
