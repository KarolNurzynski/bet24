package pl.coderslab.controllerRest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.BetOffer;
import pl.coderslab.service.BetOfferService;

import java.util.List;

/**
 * REST controller prodviding external users with basic CRUD operations on bet offers. No security provided.
 */
@RestController
@RequestMapping("/api/betOffers")
public class BetOfferRestController {

    @Autowired
    BetOfferService betOfferService;

    @GetMapping("/")
    public List<BetOffer> listOfBetOffers() {
        return betOfferService.findAllBetOffers();
    }

    @GetMapping("/{id}")
    public BetOffer getBetOfferById(@PathVariable Long id) {
        return betOfferService.findBetOfferById(id);
    }
    
    @PostMapping("/")
    public BetOffer addBetOffer(@RequestBody BetOffer betOffer) {
        return betOfferService.saveBetOffer(betOffer);
    }

    @PutMapping("/{id}")
    public BetOffer editBetOffer(@RequestBody BetOffer betOffer) {
        return betOfferService.editBetOffer(betOffer);
    }


}
