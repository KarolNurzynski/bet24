package pl.coderslab.controllerRest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Bet;
import pl.coderslab.service.BetService;

import java.util.List;

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
    public void addBet(@RequestBody Bet bet)
    {
        betService.saveBet(bet);
    }

    @PutMapping("/{id}")
    public void editBet(@RequestBody Bet bet) {
        betService.editBet(bet);
    }


    @DeleteMapping("/{id}")
    public void deleteBet(@PathVariable Long id) {
        betService.deleteBet(id);
    }


}
