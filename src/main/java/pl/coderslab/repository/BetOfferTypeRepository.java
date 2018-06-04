package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.BetOfferType;
import pl.coderslab.entity.Event;

public interface BetOfferTypeRepository extends JpaRepository<BetOfferType,Long> {


}
