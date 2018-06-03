package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Bet;

public interface BetRepository extends JpaRepository<Bet,Long> {


}
