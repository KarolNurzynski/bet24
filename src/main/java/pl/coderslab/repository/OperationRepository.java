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
    BigDecimal getSumOfAllOperationsValueByAccountId(@Param("accountId") Long accountId);

    @Query("Select SUM(o.value) from Operation o where o.user.id = :userId")
    BigDecimal getSumOfAllOperationsValueByUserId(@Param("userId") Long userId);

    List<Operation> findAllByAccountIdOrderByDateDesc(Long accountId);

    List<Operation> findAllByUserIdOrderByDateDesc(Long accountId);

}
