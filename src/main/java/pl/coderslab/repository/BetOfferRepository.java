package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.BetOffer;

import java.time.LocalTime;
import java.util.List;

public interface BetOfferRepository extends JpaRepository<BetOffer,Long> {

    List<BetOffer> findAllByFinishedIsNullAndPublishedIsNotNull();

    List<BetOffer> findAllByEvent_IdAndFinishedIsNullAndPublishedIsNotNull(Long eventId);

    List<BetOffer> findAllByFinishedIsNotNull();

    BetOffer findFirstByEvent_IdAndBetOfferType_Id(Long eventId, Long betOfferTypeId);


}
