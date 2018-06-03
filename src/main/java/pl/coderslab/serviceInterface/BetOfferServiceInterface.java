package pl.coderslab.serviceInterface;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.BetOffer;

import java.util.List;

@Service
public interface BetOfferServiceInterface {

    public List<BetOffer> findAllBetOffers();

    public BetOffer findBetOfferById(Long id);

    public BetOffer saveBetOffer(BetOffer betOffer);

    public BetOffer editBetOffer(BetOffer betOffer);

    public void deleteBetOffer(Long id);

}
