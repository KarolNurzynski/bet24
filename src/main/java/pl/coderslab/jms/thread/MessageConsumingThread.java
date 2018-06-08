package pl.coderslab.jms.thread;

import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.*;
import pl.coderslab.jms.JmsConsumer;
import pl.coderslab.service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This is a process that is used to receive JMS messages.
 * In this app JMS messages are used to send String "flags" to perform different actions
 * depending on the flag received (see {@link MessageProducingThread} for process responsible for sending of messages).
 *
 */
@Service
public class MessageConsumingThread implements Runnable {

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

                /**
                 * This action updates bet offers basing on active events
                 */

                case "update-betOffers":

                    List<Event> activeEvents = eventService.findAllActiveEvents();

                    for (Event event : activeEvents) {
                        betOfferService.generateAndUpdateBetOffersIncludingOddsBasedOnEventId(event.getId());
                    }

                    break;


                case "update-prizes":

                    /**
                     * This action finds all finished events which have some bets still active (so not paid yet)
                     * After finding these bets their active status is changed to finished ('timeend' parameter is set to current time)
                     * Then all those bets are send for payment
                     */

                    List<Event> finishedEvents = eventService.findAllFinishedEventsWithActiveBets();

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
