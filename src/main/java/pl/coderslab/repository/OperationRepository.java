package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Operation;
import pl.coderslab.entity.User;

public interface OperationRepository extends JpaRepository<Operation,Long> {


}
