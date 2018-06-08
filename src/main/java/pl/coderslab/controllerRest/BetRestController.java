package pl.coderslab.controllerRest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Bet;
import pl.coderslab.service.BetService;

import java.util.List;

/**
 *  * REST controller prodviding external users with basic CRUD operations on bets. No security provided.
 */
@RestController
@RequestMapping("/api/bets")
public class BetRestController {

    @Autowired
    BetService betService;

    @GetMapping("/")
    public List<Bet> listOfBets() {
        return betService.findAllBets();
    }

    @GetMapping("/{id}")
    public Bet getBetById(@PathVariable Long id) {
        return betService.findBetById(id);
    }
    
    @PostMapping("/")
    public Bet addBet(@RequestBody Bet bet) {
        return betService.saveBet(bet);
    }

    @PutMapping("/{id}")
    public Bet editBet(@RequestBody Bet bet) {
        return betService.editBet(bet);
    }



}
