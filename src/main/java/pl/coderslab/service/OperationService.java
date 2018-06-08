package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Operation;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface with CRUD methods for actions on database table operations. All methods names are very descriptive.
 */
@Service
public interface OperationService {

    public List<Operation> findAllOperations();

    public Operation findOperationById(Long id);

    public Operation saveOperation(Operation operation);

    public Operation editOperation(Operation operation);

    public void deleteOperation(Long id);

    public BigDecimal getSumOfAllOperationsValueByAccountId(Long id);

    public BigDecimal getSumOfAllOperationsValueByUserId(Long id);

    public List<Operation> findAllOperationsByAccountId(Long id);

    public List<Operation> findAllOperationsByUserId(Long id);

}
