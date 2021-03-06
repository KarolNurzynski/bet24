package pl.coderslab.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Bet;
import pl.coderslab.repository.BetRepository;
import pl.coderslab.service.BetService;

import java.util.List;

/**
 * Service with CRUD methods for actions on database table bets. All methods names are very descriptive.
 */
@Service
public class BetServiceImpl implements BetService {
    
    BetRepository betRepository;

    @Autowired
    BetServiceImpl(BetRepository betRepository) {
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

    public void saveListOfBets(List<Bet> listOfBets) {
        betRepository.saveAll(listOfBets);
    }

    public Bet editBet(Bet bet) {
        return betRepository.save(bet);
    }

    public void deleteBet(Long id) {
        betRepository.deleteById(id);
    }

    public List<Bet> findAllActiveBetsByUserId(Long id) {
        return betRepository.findAllByUserIdAndTimeMadeIsNotNullAndTimeEndIsNull(id);
    }

    public List<Bet> findAllBetsByUserId(Long id) {
        return betRepository.findAllByUserId(id);
    }

    @Override
    public List<Bet> findAllUnpaidFinishedBets() {
        return betRepository.findAllByTimeEndIsNotNullAndPaidStatusEquals(0);
    }

    @Override
    public List<Bet> findAllPaidFinishedBets() {
        return betRepository.findAllByTimeEndIsNotNullAndPaidStatusEquals(1);
    }

    @Override
    public List<Bet> findAllBetsByEventId(Long event_id) {
        return betRepository.findAllByEventId(event_id);
    }

}
