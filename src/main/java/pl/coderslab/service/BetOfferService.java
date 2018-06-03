package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.BetOffer;
import pl.coderslab.repository.BetOfferRepository;
import pl.coderslab.serviceInterface.BetOfferServiceInterface;

import java.util.List;

@Service
public class BetOfferService implements BetOfferServiceInterface {
    
    BetOfferRepository betOfferRepository;

    @Autowired
    BetOfferService(BetOfferRepository betOfferRepository) {
        this.betOfferRepository=betOfferRepository;
    }

    public List<BetOffer> findAllBetOffers() {
        return betOfferRepository.findAll();
    }

    public BetOffer findBetOfferById(Long id) {
        return betOfferRepository.findById(id).orElseGet(null);
    }

    public BetOffer saveBetOffer(BetOffer betOffer) {
        return betOfferRepository.save(betOffer);
    }

    public BetOffer editBetOffer(BetOffer betOffer) {
        return betOfferRepository.save(betOffer);
    }

    public void deleteBetOffer(Long id) {
        betOfferRepository.deleteById(id);
    }

}
