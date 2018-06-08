package pl.coderslab.event24;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface providing methods to perform basic CRUD operations on events table (faking external data provider)
 */
@Service
public interface Event24Service {

    public List<Event24> findAllEvent24();

    public Event24 findEvent24ById(Long id);

    public Event24 saveEvent24(Event24 event);

    public Event24 editEvent24(Event24 event);

    public void deleteEvent24(Long id);

    List<Event24> findAllActiveEvent24();


}
