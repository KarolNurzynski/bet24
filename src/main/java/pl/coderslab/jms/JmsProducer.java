package pl.coderslab.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.LiveEventDto;

import java.util.List;

/**
 * This class contains methods for sending JMS messages
 */
@Component
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    String destinationQueue;

    public void send(String msg){
        jmsTemplate.convertAndSend(destinationQueue, msg);
    }

    public void sendEvent(LiveEventDto event){
        jmsTemplate.convertAndSend(destinationQueue, event);
    }

    public void sendEvents(List<LiveEventDto> events){
        jmsTemplate.convertAndSend(destinationQueue, events);
    }


}