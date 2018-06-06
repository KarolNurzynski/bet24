package pl.coderslab.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.Event;

@Component
public class JmsConsumer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    String destinationQueue;

    public String receive(){
        return (String)jmsTemplate.receiveAndConvert(destinationQueue);
    }

    public Event receiveEvent(){
        return (Event)jmsTemplate.receiveAndConvert(destinationQueue);
    }
}
