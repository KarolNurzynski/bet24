package pl.coderslab.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.entity.Event;
import pl.coderslab.service.EventService;

@RestController
public class WebController {

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
        Event event = eventService.findEventById(1L);
        jsmClient.sendEvent(event);
        return "Done";
    }

    @RequestMapping(value="/receiveEvent")
    public Event receiveEvent(){
        return jsmClient.receiveEvent();
    }
}