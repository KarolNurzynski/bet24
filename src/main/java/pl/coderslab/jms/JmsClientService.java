package pl.coderslab.jms;

import pl.coderslab.entity.Event;

public interface JmsClientService {

    public void send(String msg);
    public String receive();

    public void sendEvent(Event event);
    public Event receiveEvent();
}