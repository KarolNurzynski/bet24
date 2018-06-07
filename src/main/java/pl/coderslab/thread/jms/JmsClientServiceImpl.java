package pl.coderslab.thread.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.dto.LiveEventDto;

import java.util.List;

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
    public void sendEvent(LiveEventDto event) {
        jmsProducer.sendEvent(event);
    }

    @Override
    public LiveEventDto receiveEvent() {
        return jmsConsumer.receiveEvent();
    }

    @Override
    public void sendEvents(List<LiveEventDto> events) {
        jmsProducer.sendEvents(events);
    }

    @Override
    public List<LiveEventDto> receiveEvents() {
        return jmsConsumer.receiveEvents();
    }
}