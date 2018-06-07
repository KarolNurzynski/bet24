package pl.coderslab.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.LiveEventDto;
import pl.coderslab.entity.Event;

import javax.jms.ObjectMessage;
import java.util.List;

@Component
public class JmsConsumer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    String destinationQueue;

    public String receive(){
        return (String)jmsTemplate.receiveAndConvert(destinationQueue);
    }

    public LiveEventDto receiveEvent(){

        return (LiveEventDto) jmsTemplate.receiveAndConvert(destinationQueue);
    }

    public List<LiveEventDto> receiveEvents(){

        return (List<LiveEventDto>) jmsTemplate.receiveAndConvert(destinationQueue);
    }
}
