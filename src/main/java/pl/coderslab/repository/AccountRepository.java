package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Account;
import pl.coderslab.entity.Event;

public interface AccountRepository extends JpaRepository<Account,Long> {


}
