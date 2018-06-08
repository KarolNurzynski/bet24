package pl.coderslab.jms.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.jms.JmsProducer;

import java.util.concurrent.TimeUnit;

/**
 * This is a process that is used to produce JMS messages.
 * In this app JMS messages are used to send String "flags" to perform different
 * actions depending on the flag received (see {@link MessageConsumingThread} for process responsible for sending of messages).
 */
@Service
public class MessageProducingThread implements Runnable {

    @Autowired
    JmsProducer jmsProducer;

    @Override
    public void run() {

        while(true) {

            jmsProducer.send("update-betOffers");
            jmsProducer.send("update-prizes");

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
