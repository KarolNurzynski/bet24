package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.app.OddsCalculator;
import pl.coderslab.entity.Bet;
import pl.coderslab.entity.BetOffer;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.User;
import pl.coderslab.service.BetOfferService;
import pl.coderslab.service.EventService;
import pl.coderslab.service.UserService;
import pl.coderslab.serviceImpl.OddsServiceImpl;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/update")
public class UpdateController {

    @Autowired
    EventService eventService;

    @Autowired
    BetOfferService betOfferService;

    @Autowired
    OddsServiceImpl oddsService;

    @GetMapping("/newBetOffer")
    public BetOffer addNewBetOffer(){

        BetOffer betOffer = oddsService.generateBetOfferFromEvent(1L, 1);
        return betOffer;
    }

//    @GetMapping("/newBetOffers/{event_id}")
//    public List<BetOffer> addThreeNewBetOffers(@PathVariable Long event_id){
//
//        List<BetOffer> newBetOffers = oddsService.generateAllBetOffersFromEvent(event_id);
//
//        return newBetOffers;
//    }

    @GetMapping("/newBetOffers/{event_id}")
    public List<BetOffer> addThreeNewBetOffers(@PathVariable Long event_id){

        return betOfferService.generateOrUpdateBetOffersBasedOnEventId(event_id);
    }

}
