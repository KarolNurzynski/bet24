package pl.coderslab.serviceInterface;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.BetOfferType;

import java.util.List;

@Service
public interface BetOfferTypeServiceInterface {

    public List<BetOfferType> findAllBetOfferTypes();

    public BetOfferType findBetOfferTypeById(Long id);

    public BetOfferType saveBetOfferType(BetOfferType betOfferType);

    public BetOfferType editBetOfferType(BetOfferType betOfferType);

    public void deleteBetOfferType(Long id);

}
