package pl.coderslab.jms;

import pl.coderslab.dto.LiveEventDto;
import pl.coderslab.entity.Event;

import java.util.List;

public interface JmsClientService {

    public void send(String msg);
    public String receive();

    public void sendEvent(LiveEventDto event);
    public LiveEventDto receiveEvent();

    public void sendEvents(List<LiveEventDto> events);
    public List<LiveEventDto> receiveEvents();
}