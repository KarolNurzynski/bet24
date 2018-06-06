package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Bet;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet,Long> {

    List<Bet> findAllByUserIdAndTimeMadeIsNotNullAndTimeEndIsNull(Long userId);
    List<Bet> findAllByUserId(Long userId);

}
