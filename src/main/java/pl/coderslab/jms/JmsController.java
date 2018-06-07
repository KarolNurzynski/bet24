package pl.coderslab.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dto.LiveEventDto;
import pl.coderslab.entity.Event;
import pl.coderslab.service.EventService;

import javax.jms.ObjectMessage;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JmsController {

    @Autowired
    EventService eventService;

    @Autowired
    JmsClientService jsmClient;

    @RequestMapping(value="/produce")
    public String produce(@RequestParam("msg")String msg){
        jsmClient.send(msg);
        return "Done";
    }

    @RequestMapping(value="/receive")
    public String receive(){
        return jsmClient.receive();
    }


    @RequestMapping(value="/produceEvent")
    public String produceEvent(){
        Event e = eventService.findEventById(1L);
        byte scoreA = (byte) e.getScoreA();
        byte scoreB = (byte) e.getScoreB();
        String teamA = e.getTeamA();
        String teamB = e.getTeamB();
        LiveEventDto event = new LiveEventDto(teamA, scoreA, teamB, scoreB);
        jsmClient.sendEvent(event);
        return "Done";
    }

    @RequestMapping(value="/receiveEvent")
    public LiveEventDto receiveEvent(){
        return jsmClient.receiveEvent();
    }

    @RequestMapping(value="/produceEvents")
    public String produceEvents(){
        List<Event> events = eventService.findAllActiveEvents();
        List<LiveEventDto> liveEvents = new ArrayList<>();

        for (Event e:events) {
            byte scoreA = (byte) e.getScoreA();
            byte scoreB = (byte) e.getScoreB();
            String teamA = e.getTeamA();
            String teamB = e.getTeamB();
            LiveEventDto liveEvent = new LiveEventDto(teamA, scoreA, teamB, scoreB);
            liveEvents.add(liveEvent);
        }

        jsmClient.sendEvents(liveEvents);
        return "Done";
    }

    @RequestMapping(value="/receiveEvents")
    public List<LiveEventDto> receiveEvents(){
        return jsmClient.receiveEvents();
    }
}