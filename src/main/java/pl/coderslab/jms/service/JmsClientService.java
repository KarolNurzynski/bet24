package pl.coderslab.jms.service;

import pl.coderslab.dto.LiveEventDto;

import java.util.List;

/**
 * Interface providing methods for sending and receiving JMS messages
 * Only String message are currently in use
 */
public interface JmsClientService {

    public void send(String msg);
    public String receive();

    public void sendEvent(LiveEventDto event);
    public LiveEventDto receiveEvent();

    public void sendEvents(List<LiveEventDto> events);
    public List<LiveEventDto> receiveEvents();
}