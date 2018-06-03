package pl.coderslab.controllerRest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.BetOffer;
import pl.coderslab.service.BetOfferService;

import java.util.List;

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
    public void addBetOffer(@RequestBody BetOffer betOffer)
    {
        betOfferService.saveBetOffer(betOffer);
    }

    @PutMapping("/{id}")
    public void editBetOffer(@RequestBody BetOffer betOffer) {
        betOfferService.editBetOffer(betOffer);
    }


    @DeleteMapping("/{id}")
    public void deleteBetOffer(@PathVariable Long id) {
        betOfferService.deleteBetOffer(id);
    }


}
