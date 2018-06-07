package pl.coderslab.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.*;
import pl.coderslab.thread.jms.JmsConsumer;
import pl.coderslab.service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MessageReceiver implements Runnable {

    @Autowired
    EventService eventService;

    @Autowired
    BetOfferService betOfferService;

    @Autowired
    OperationService operationService;


    @Autowired
    AccountService accountService;

    @Autowired
    BetService betService;

    @Autowired
    JmsConsumer jmsConsumer;

    @Override
    public void run() {
        while(true) {
            String receivedMessage = jmsConsumer.receive();

            switch (receivedMessage) {
                case "update-betOffers":

                    List<Event> activeEvents = eventService.findAllActiveEvents();

                    for (Event event : activeEvents) {

                        betOfferService.saveOrUpdateBetOffersBasedOnEventId(event.getId());

                    }

                    break;


                case "update-prizes":

                    List<Event> finishedEvents = eventService.findAllEventsWithNotEndedBets();

                    for (Event event:finishedEvents) {
                        List<Bet> betsToDeactivate = betService.findAllBetsByEventId(event.getId());
                        for (Bet bet:betsToDeactivate) {
                            System.out.println(bet.getId());
                            bet.setTimeEnd(LocalDateTime.now());
                            betService.saveBet(bet);
                        }
                    }

                    List<Bet> unpaidBets = betService.findAllUnpaidFinishedBets();

                    for (Bet bet : unpaidBets) {
                        User user = bet.getUser();
                        Account account = accountService.findActiveUserAccount(user.getId());

                        Operation operation = new Operation();
                        operation.setValue(bet.getStake());
                        operation.setDate(LocalDateTime.now());
                        operation.setAccount(account);
                        operation.setUser(user);
                        operation.setDescription("Reward payment for bet nr " + bet.getId());

                        bet.setPaidStatus(1);
                        betService.saveBet(bet);

                        operationService.saveOperation(operation);
                    }

                    break;
            }

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
