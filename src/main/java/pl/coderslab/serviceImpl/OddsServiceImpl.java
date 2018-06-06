package pl.coderslab.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.app.OddsCalculator;
import pl.coderslab.entity.*;
import pl.coderslab.event24.Event24Service;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.BetOfferService;
import pl.coderslab.service.BetOfferTypeService;
import pl.coderslab.service.EventService;
import pl.coderslab.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class OddsServiceImpl {

    @Autowired
    EventService eventService;

    @Autowired
    BetOfferService betOfferService;

    @Autowired
    BetOfferTypeService betOfferTypeService;

    private final OddsCalculator oddsCalculator;


    // Check out units - odds in program are 0-100; odds in calculator 0.00-1.00
    @Autowired
    public OddsServiceImpl(OddsCalculator oddsCalculator) {
        this.oddsCalculator = oddsCalculator;
    }

    public BetOffer generateBetOfferFromEvent(Long event_id, int offerTypeIndex) {

        Event event = eventService.findEventById(event_id);

        double strengthA = (double) (event.getStrengthA()/100.0);
        double strengthB = (double) (event.getStrengthB()/100.0);


        List<Double> probabilities = OddsCalculator.calculateProbabilityAwinsBwinsDraw(strengthA, strengthB);

        double oddsAWins = 1.0 / probabilities.get(0);

        BetOfferType betOfferType = new BetOfferType();
        betOfferType.setId(1L);
        betOfferType.setName("aWins");

        BetOffer betOffer = new BetOffer();
        betOffer.setBetOfferType(betOfferType);
        betOffer.setEvent(event);
        betOffer.setOdds(BigDecimal.valueOf(oddsAWins));
        betOffer.setPublished(LocalDateTime.now());

        return betOfferService.saveBetOffer(betOffer);

    }

    public List<BetOffer> generateAllBetOffersFromEvent(Long event_id) throws NullPointerException {

        Event event = eventService.findEventById(event_id);

        double strengthA = (double) (event.getStrengthA()/100.0);
        double strengthB = (double) (event.getStrengthB()/100.0);

        List<Double> probabilities = OddsCalculator.calculateProbabilityAwinsBwinsDraw(strengthA, strengthB);

        List<BetOffer> updatedBetOffers = new ArrayList<>();
        List<BetOffer> savedUpdatedBetOffers = new ArrayList<>();


        for (int i=0;i<3;i++) {

            Long betOfferType_id = Long.valueOf(i+1);

            double oddsAWins = (probabilities.get(i) / (1.0 - probabilities.get(i))) + 1.0;


            BetOfferType betOfferType = betOfferTypeService.findBetOfferTypeById(betOfferType_id);

            BetOffer betOffer = betOfferService.findFirstByEvent_IdAndBetOfferType_Id(event_id, betOfferType_id);

            if (betOffer==null) {
                betOffer = new BetOffer();
            }

            betOffer.setBetOfferType(betOfferType);
            betOffer.setEvent(event);
            betOffer.setOdds(BigDecimal.valueOf(oddsAWins));
            betOffer.setPublished(LocalDateTime.now());
            betOffer.setFinished(null);
            updatedBetOffers.add(betOffer);

        }


        for (BetOffer betOffer:updatedBetOffers) {
            BetOffer savedOffer = betOfferService.editBetOffer(betOffer);
            savedUpdatedBetOffers.add(savedOffer);
        }

        return savedUpdatedBetOffers;

    }

    public List<BetOffer> generateAllBetOffersFromEventInclTimeAndCurrentResult (Long event_id, double timeToEndRatio, int scoreA, int scoreB) throws NullPointerException {

        Event event = eventService.findEventById(event_id);

        double strengthA = (double) (event.getStrengthA()/100.0);
        double strengthB = (double) (event.getStrengthB()/100.0);

        List<Double> probabilities = OddsCalculator.calculateProbabilityAwinsBwinsDrawInclTimeAndCurrentResult (strengthA, strengthB, timeToEndRatio, scoreA, scoreB);

        List<BetOffer> updatedBetOffers = new ArrayList<>();
        List<BetOffer> savedUpdatedBetOffers = new ArrayList<>();


        for (int i=0;i<3;i++) {

            Long betOfferType_id = Long.valueOf(i+1);

            double oddsAWins = (probabilities.get(i) / (1.0 - probabilities.get(i))) + 1.0;


            BetOfferType betOfferType = betOfferTypeService.findBetOfferTypeById(betOfferType_id);

            BetOffer betOffer = betOfferService.findFirstByEvent_IdAndBetOfferType_Id(event_id, betOfferType_id);

            if (betOffer==null) {
                betOffer = new BetOffer();
            }

            betOffer.setBetOfferType(betOfferType);
            betOffer.setEvent(event);
            betOffer.setOdds(BigDecimal.valueOf(oddsAWins));
            betOffer.setPublished(LocalDateTime.now());
            betOffer.setFinished(null);
            updatedBetOffers.add(betOffer);

        }


        for (BetOffer betOffer:updatedBetOffers) {
            BetOffer savedOffer = betOfferService.editBetOffer(betOffer);
            savedUpdatedBetOffers.add(savedOffer);
        }

        return savedUpdatedBetOffers;

    }


}
