package pl.coderslab.serviceInterface;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Operation;

import java.util.List;

@Service
public interface OperationServiceInterface {

    public List<Operation> findAllOperations();

    public Operation findOperationById(Long id);

    public Operation saveOperation(Operation operation);

    public Operation editOperation(Operation operation);

    public void deleteOperation(Long id);

}
