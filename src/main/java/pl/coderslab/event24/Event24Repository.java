package pl.coderslab.event24;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Event;

import java.time.LocalTime;
import java.util.List;

public interface Event24Repository extends JpaRepository<Event24,Long> {

    List<Event24> findAllByTimeLeftIsBefore(LocalTime timeLeft);

}
