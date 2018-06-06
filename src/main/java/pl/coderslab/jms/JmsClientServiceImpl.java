package pl.coderslab.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Event;

@Service
public class JmsClientServiceImpl implements JmsClientService {

    @Autowired
    JmsConsumer jmsConsumer;

    @Autowired
    JmsProducer jmsProducer;

    @Override
    public void send(String msg) {
        jmsProducer.send(msg);
    }

    @Override
    public String receive() {
        return jmsConsumer.receive();
    }

    @Override
    public void sendEvent(Event event) {
        jmsProducer.sendEvent(event);
    }

    @Override
    public Event receiveEvent() {
        return jmsConsumer.receiveEvent();
    }
}