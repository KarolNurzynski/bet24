package pl.coderslab.event2424;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.event24.Event24;
import pl.coderslab.event24.Event24Repository;
import pl.coderslab.event24.Event24Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class Event24ServiceImpl implements Event24Service {

    Event24Repository event24Repository;

    @Autowired
    Event24ServiceImpl(Event24Repository event24Repository) {
        this.event24Repository=event24Repository;
    }

    public List<Event24> findAllEvent24() {
        return event24Repository.findAll();
    }

    public Event24 findEvent24ById(Long id) {
        return event24Repository.findById(id).orElseGet(null);
    }

    public Event24 saveEvent24(Event24 event24) {
        return event24Repository.save(event24);
    }

    public Event24 editEvent24(Event24 event24) {
        return event24Repository.save(event24);
    }

    public void deleteEvent24(Long id) {
        event24Repository.deleteById(id);
    }

    public List<Event24> findAllActiveEvent24() {
        return event24Repository.findAllByTimeLeftIsBefore(LocalTime.of(0,0,5));
    }

//    @Scheduled(fixedRate = 1000)
//    public List<Event24> updateAllActiveEvent24() {
//        return null;
//    }


}
