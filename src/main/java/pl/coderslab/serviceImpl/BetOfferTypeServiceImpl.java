package pl.coderslab.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.BetOfferType;
import pl.coderslab.repository.BetOfferTypeRepository;
import pl.coderslab.service.BetOfferTypeService;

import java.util.List;

/**
 * Service with CRUD methods for actions on database table bet_offer_types. All methods names are very descriptive.
 */
@Service
public class BetOfferTypeServiceImpl implements BetOfferTypeService {
    
    BetOfferTypeRepository betOfferTypeRepository;

    @Autowired
    BetOfferTypeServiceImpl(BetOfferTypeRepository betOfferTypeRepository) {
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
