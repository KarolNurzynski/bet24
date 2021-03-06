package pl.coderslab.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Operation;
import pl.coderslab.repository.OperationRepository;
import pl.coderslab.service.OperationService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service with CRUD methods for actions on database table operations. All methods names are very descriptive.
 */
@Service
public class OperationServiceImpl implements OperationService {

    OperationRepository operationRepository;

    @Autowired
    OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository=operationRepository;
    }

    public List<Operation> findAllOperations() {
        return operationRepository.findAll();
    }

    public Operation findOperationById(Long id) {
        return operationRepository.findById(id).orElseGet(null);
    }

    public Operation saveOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    public Operation editOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    public void deleteOperation(Long id) {
        operationRepository.deleteById(id);
    }

    public BigDecimal getSumOfAllOperationsValueByAccountId(Long id) {
        return operationRepository.getSumOfAllOperationsValueByAccountId(id);
    }

    public BigDecimal getSumOfAllOperationsValueByUserId(Long id) {
        return operationRepository.getSumOfAllOperationsValueByUserId(id);
    }

    public List<Operation> findAllOperationsByAccountId(Long id) {
        return operationRepository.findAllByAccountIdOrderByDateDesc(id);
    }

    public List<Operation> findAllOperationsByUserId(Long user_id) {

        return operationRepository.findAllByUserIdOrderByDateDesc(user_id);
    }

}
