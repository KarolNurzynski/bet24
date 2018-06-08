package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.BetOfferType;

import java.util.List;

/**
 * Interface with CRUD methods for actions on database table bet_offer_types. All methods names are very descriptive.
 */
@Service
public interface BetOfferTypeService {

    public List<BetOfferType> findAllBetOfferTypes();

    public BetOfferType findBetOfferTypeById(Long id);

    public BetOfferType saveBetOfferType(BetOfferType betOfferType);

    public BetOfferType editBetOfferType(BetOfferType betOfferType);

    public void deleteBetOfferType(Long id);

}
