package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.BetOffer;

import java.util.List;

public interface BetOfferRepository extends JpaRepository<BetOffer,Long> {

    List<BetOffer> findAllByFinishedIsNullAndPublishedIsNotNull();

}
