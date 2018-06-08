package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Event;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findAllByTimeLeftIsAfter(LocalTime timeLeft);

    List<Event> findAllByTimeLeftIsBefore(LocalTime timeLeft);

    List<Event> findAllByTimeLeftEquals(LocalTime timeLeft);

    @Query("Select e From Event e Join e.betOffers bo Join bo.bets b Where b.timeEnd is NULL and e.timeLeft = '00:00:00'")
    List<Event> findAllFinishedEventsWithActiveBets();

}
