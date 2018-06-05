package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.entity.Operation;
import pl.coderslab.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long> {

    @Query("Select SUM(o.value) from Operation o where o.account.id = :accountId")
    BigDecimal getSumOfAllOperationsValue(@Param("accountId") Long accountId);

    List<Operation> findAllByAccountIdOrderByDateDesc(Long accountId);

}
