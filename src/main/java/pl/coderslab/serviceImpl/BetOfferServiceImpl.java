package pl.coderslab.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.app.OddsCalculator;
import pl.coderslab.entity.BetOffer;
import pl.coderslab.entity.Event;
import pl.coderslab.repository.BetOfferRepository;
import pl.coderslab.repository.EventRepository;
import pl.coderslab.service.BetOfferService;
import pl.coderslab.service.EventService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class BetOfferServiceImpl implements BetOfferService {

    @Autowired
    EventService eventService;

    @Autowired
    OddsServiceImpl oddsService;
    
    BetOfferRepository betOfferRepository;

    @Autowired
    BetOfferServiceImpl(BetOfferRepository betOfferRepository) {
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

    public List<BetOffer> findAllActiveBetOffers() {
        return betOfferRepository.findAllByFinishedIsNullAndPublishedIsNotNull();
    }

    public BetOffer findFirstByEvent_IdAndBetOfferType_Id(Long eventId, Long betOfferTypeId) {

        return betOfferRepository.findFirstByEvent_IdAndBetOfferType_Id(eventId, betOfferTypeId);

    }

    @Override
    public void changeAllBetOffersToFinishedByEventId(Long event_id) {
        List<BetOffer> betOffers = betOfferRepository.findAllByEvent_IdAndFinishedIsNullAndPublishedIsNotNull(event_id);
        for (BetOffer betOffer : betOffers) {
            betOffer.setFinished(LocalDateTime.now());
            betOfferRepository.save(betOffer);
        }
    }

    @Override
    public List<BetOffer> findAllActiveBetOffersByEvent_Id(Long eventId) {
        return betOfferRepository.findAllByEvent_IdAndFinishedIsNullAndPublishedIsNotNull(eventId);
    }

    @Override
    public List<BetOffer> saveOrUpdateBetOffersBasedOnEventId(Long eventId) {

        Event event = eventService.findEventById(eventId);

        int scoreA = event.getScoreA();
        int scoreB = event.getScoreB();

        LocalTime timeLeft = event.getTimeLeft();
        LocalTime totalTime = LocalTime.of(1,30,0);

        double timeToEndRatio = OddsCalculator.calcTimeToEndRation(totalTime, timeLeft);

        List<BetOffer> newBetOffers = oddsService.generateAllBetOffersFromEventInclTimeAndCurrentResult(eventId, timeToEndRatio, scoreA, scoreB);

        return betOfferRepository.saveAll(newBetOffers);
    }

    @Override
    public List<BetOffer> findAllFinishedBetOffers() {

        return betOfferRepository.findAllByFinishedIsNotNull();

    }

}
