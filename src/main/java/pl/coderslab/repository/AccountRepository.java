package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Account;
import pl.coderslab.entity.Event;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findAllByUserId(Long id);

    Account findOneByUserIdAndActiveStatusIsTrue(Long id);

}
