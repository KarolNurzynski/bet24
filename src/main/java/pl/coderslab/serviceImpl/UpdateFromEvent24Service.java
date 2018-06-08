package pl.coderslab.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.dto.EventDto;
import pl.coderslab.entity.Event;
import pl.coderslab.service.EventService;

import javax.validation.Valid;
import java.util.List;

/**
 * This service is resposible for updating data from external end point (external data provider - event24 service)
 * Data is requested every second with scheduled method
 */
@Service
public class UpdateFromEvent24Service {

    @Autowired
    EventService eventService;

    @Scheduled(fixedRate = 1000)
    public void getEventsFromEvent24Service() {

        String url = "http://localhost:8080/api/event24/";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<EventDto[]> responseEvents = restTemplate.getForEntity(
                url, EventDto[].class);
        EventDto[] events = responseEvents.getBody();

        for (EventDto eventDto: events) {
            Event event = eventService.saveEventFromEventDto(eventDto);
        }
    }

}
