package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.BetOfferType;
import pl.coderslab.repository.BetOfferTypeRepository;
import pl.coderslab.serviceInterface.BetOfferTypeServiceInterface;

import java.util.List;

@Service
public class BetOfferTypeService implements BetOfferTypeServiceInterface {
    
    BetOfferTypeRepository betOfferTypeRepository;

    @Autowired
    BetOfferTypeService(BetOfferTypeRepository betOfferTypeRepository) {
        this.betOfferTypeRepository=betOfferTypeRepository;
    }

    public List<BetOfferType> findAllBetOfferTypes() {
        return betOfferTypeRepository.findAll();
    }

    public BetOfferType findBetOfferTypeById(Long id) {
        return betOfferTypeRepository.findById(id).orElseGet(null);
    }

    public BetOfferType saveBetOfferType(BetOfferType betOfferType) {
        return betOfferTypeRepository.save(betOfferType);
    }

    public BetOfferType editBetOfferType(BetOfferType betOfferType) {
        return betOfferTypeRepository.save(betOfferType);
    }

    public void deleteBetOfferType(Long id) {
        betOfferTypeRepository.deleteById(id);
    }

}
