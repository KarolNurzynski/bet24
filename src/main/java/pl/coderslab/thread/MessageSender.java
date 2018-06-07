package pl.coderslab.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.thread.jms.JmsProducer;

import java.util.concurrent.TimeUnit;

@Service
public class MessageSender implements Runnable {

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
