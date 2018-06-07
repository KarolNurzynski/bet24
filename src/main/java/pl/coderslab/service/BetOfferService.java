package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.BetOffer;

import java.util.List;

@Service
public interface BetOfferService {

    public List<BetOffer> findAllBetOffers();

    public BetOffer findBetOfferById(Long id);

    public BetOffer saveBetOffer(BetOffer betOffer);

    public BetOffer editBetOffer(BetOffer betOffer);

    public void deleteBetOffer(Long id);

    List<BetOffer> findAllActiveBetOffers();

    BetOffer findFirstByEvent_IdAndBetOfferType_Id(Long eventId, Long betOfferTypeId);

    void changeAllBetOffersToFinishedByEventId(Long event_id);

    List<BetOffer> findAllActiveBetOffersByEvent_Id(Long eventId);

    List<BetOffer> saveOrUpdateBetOffersBasedOnEventId(Long eventId);

    List<BetOffer> findAllFinishedBetOffers();

}
